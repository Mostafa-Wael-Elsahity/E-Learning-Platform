package com.example.elearningplatform.course.lesson;

import java.io.IOException;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elearningplatform.course.lesson.dto.CreateLessonRequest;
import com.example.elearningplatform.course.lesson.dto.UpdateLessonRequest;
import com.example.elearningplatform.course.lesson.dto.UploadVideoRequest;
import com.example.elearningplatform.response.Response;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;

    /*************************************************************************************** */

    @GetMapping("/get-lesson/{lessonId}/{pageNumber}")
    public Response getLesson(@PathVariable("lessonId") Integer lessonId,
            @PathVariable("pageNumber") Integer pageNumber) {
        return lessonService.getLesson(lessonId, pageNumber);
    }

    /*********************************************************************************************/
    @PostMapping("/create-lesson")
    public Response createLesson(@RequestBody @Valid CreateLessonRequest createLessonRequest,
            BindingResult bindingResult) throws IOException, InterruptedException {

        if (bindingResult.hasErrors()) {
            return new Response(HttpStatus.BAD_REQUEST, "Validation Error", bindingResult.getAllErrors());
        }
        return lessonService.createLesson(createLessonRequest);
    }

    /***************************************************************************************/

    @PutMapping(value = "/upload-video", consumes = { "multipart/form-data" })
    public Response uploadVideo(UploadVideoRequest uploadVideoRequest)
            throws IOException, InterruptedException {

        return lessonService.uploadLessonVideo(uploadVideoRequest);
    }

    /***************************************************************************************/
    @PostMapping("/update-lesson")
    public Response updateLesson(@RequestBody @Valid UpdateLessonRequest updateLessonRequest,BindingResult bindingResult)
            throws IOException, InterruptedException {
        if (bindingResult.hasErrors()) {
            return new Response(HttpStatus.BAD_REQUEST, "Validation Error", bindingResult.getAllErrors());
        }

        return lessonService.updateLesson(updateLessonRequest);
    }
    /***************************************************************************************/
    @PostMapping(value = "/update-video", consumes = { "multipart/form-data" })
    public Response updateVideo(UploadVideoRequest uploadVideoRequest)
            throws IOException, InterruptedException {


        return lessonService.updateVideo(uploadVideoRequest);
    }

    /***************************************************************************************/

    @DeleteMapping("/delete-lesson/{lessonId}")
    public Response deleteLesson(@PathVariable("lessonId") Integer lessonId) {

        return lessonService.deleteLesson(lessonId);
    }
    /***************************************************************************************/
}
