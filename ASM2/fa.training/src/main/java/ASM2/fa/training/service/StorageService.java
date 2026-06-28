package ASM2.fa.training.service;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;

/*
    @Service: Spring Service
    Spring se tu tao Object: StorageService storageService = new StorageService();
    va quan ly no trong IoC Container
 */
@Service
public class StorageService {
    // Create directory to "uploads" folder
    private final Path rootLocation = Paths.get("uploads");

    // @PostConstruct: đánh dấu method sẽ đc gọi sau khi Spring khởi tạo
    @PostConstruct
    public void init(){
        try{
            // if directory not exist -> create it
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage service",e);
        }
    }

    // Store file uploaded into "uploads" folder
    public void store(MultipartFile file) throws IOException {
        // In case: Submit without file
        if(file.isEmpty()){
            throw new RuntimeException("Empty file");
        }

        /*
            Stream read byte by byte
            get file name -> combine directory: uploads/req.txt
            if exist -> replace (ghi đè)
            -> problem Path Traversal Attack
         */

//        Files.copy(
//                file.getInputStream(),
//                   rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())),
//                StandardCopyOption.REPLACE_EXISTING
//        );

        Path uploadRoot = rootLocation.toAbsolutePath().normalize();
        Path destinationFile =
                uploadRoot
                .resolve(Objects.requireNonNull(file.getOriginalFilename()))
                .normalize();

        if(!destinationFile.startsWith(uploadRoot)){
            throw new RuntimeException("Cannot store file outside uploads directory");
        }

        Files.copy(
            file.getInputStream(),
            destinationFile,
            StandardCopyOption.REPLACE_EXISTING
        );
    }

    // Read stored file and return resource
    public Resource loadAsResource(String fileName){
        try{
            // Create directory
            Path uploadRoot = rootLocation.toAbsolutePath().normalize();
            Path file = rootLocation.resolve(fileName).normalize();

            if(!file.startsWith(uploadRoot)){
                throw new RuntimeException("Cannot load file outside uploads directory");
            }

            // Convert to uri -> Create resource
            Resource resource = new UrlResource(file.toUri());

            // Check file
            if(resource.exists() && resource.isReadable()){
                return resource;
            }

            // file not exist
            throw new RuntimeException("Could not read file: " + fileName);
        } catch (MalformedURLException e) {
            // URL error
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }
}
