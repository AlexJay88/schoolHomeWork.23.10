package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.RepositoryAvatar;

import java.awt.print.Pageable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentService studentService;
    private final RepositoryAvatar repositoryAvatar;

    public AvatarServiceImpl(StudentService studentService, RepositoryAvatar repositoryAvatar,
                             @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.studentService = studentService;
        this.repositoryAvatar = repositoryAvatar;
        this.avatarsDir = avatarsDir;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.read(studentId);
        Path filePath = saveToFile(student, avatarFile);

        saveToDb(filePath, avatarFile, student);


    }

    private Path saveToFile(Student student, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(avatarsDir,
                student.getId() + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream inputStream = avatarFile.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
                OutputStream outputStream = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
        return filePath;
    }

    private void saveToDb(Path filePath, MultipartFile avatarFile, Student student) throws IOException {
        Avatar avatar = repositoryAvatar.findByStudent_id(student.getId()).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());

        repositoryAvatar.save(avatar);

    }

    @Override
    public Avatar readFromDB(long id) {
        return repositoryAvatar.findById(id)
                .orElseThrow(() -> new AvatarNotFoundException("Аватар не найден"));
    }

    @Override
    public File readFromFile(long id) throws IOException {
        Avatar avatar = readFromDB(id);
        Path path = Path.of(avatar.getFilePath());

        return new File(path.toString());
    }

    @Override
    public List<Avatar> findAvatarByPage(int pageNumber, int pageSize) {
        Pageable paging = (Pageable) PageRequest.of(pageNumber, pageSize);
        return repositoryAvatar.findAll((Sort) paging);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
