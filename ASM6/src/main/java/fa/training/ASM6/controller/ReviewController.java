package fa.training.ASM6.controller;

import fa.training.ASM6.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Public controller displaying the most recent approved reviews
 * across all courses for the community feed page
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public String getReviews(
            @RequestParam(defaultValue = "0") int page,
            Model model
    ){
        model.addAttribute("reviews",reviewService.findByStatus(ReviewService.STATUS_APPROVED));
        return "public/reviews";
    }
}
