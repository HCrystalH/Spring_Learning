package ASM2.fa.training.controller;

import ASM2.fa.training.entity.AssessmentMaterial;
import ASM2.fa.training.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/assessments")
public class AssessmentController {

    private final StorageService storageService;

    // In-memory list to hold assessment metadata (per requirements, no DB yet)
    private final List<AssessmentMaterial> materials = new ArrayList<>();

    // Simple counter to generate unique IDs for each AssessmentMaterial
    private final AtomicLong counter = new AtomicLong(1);

    // Constructor Injection: Spring auto-wires StorageService bean
    public AssessmentController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * GET /assessments
     * Displays the upload form and lists all uploaded materials.
     */
    @GetMapping
    public String viewAssessment(Model model) {
        model.addAttribute("materials", materials);
        return "assessments";
    }

    /**
     * POST /assessments/upload
     * Accepts title, description, and a file; saves the file to disk,
     * creates metadata, and redirects back to the listing page.
     */
    @PostMapping("/upload")
    public String uploadAssessment(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        // Manual validation: title must not be blank (@Valid doesn't work on @RequestParam)
        if (title == null || title.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Title cannot be empty");
            return "redirect:/assessments";
        }

        try {
            // Delegate file storage to StorageService
            storageService.store(file);

            // Create metadata object and add to in-memory list
            AssessmentMaterial assessmentMaterial = new AssessmentMaterial(
                    counter.getAndIncrement(),
                    title.trim(),
                    description,
                    Objects.requireNonNull(file.getOriginalFilename())
            );
            materials.add(assessmentMaterial);

            redirectAttributes.addFlashAttribute("message", "Assessment has been uploaded successfully");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Upload failed: " + e.getMessage());
        }

        return "redirect:/assessments";
    }

    /**
     * GET /assessments/download/{filename}
     * Serves the requested file as a downloadable attachment.
     */
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable("filename") String fileName
    ) {
        Resource resource = storageService.loadAsResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
