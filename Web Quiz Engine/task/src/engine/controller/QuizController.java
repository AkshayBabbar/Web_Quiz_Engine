package engine.controller;

import engine.entity.Answer;
import engine.entity.Quiz;
import engine.entity.Response;
import engine.entity.User;
import engine.repository.QuizRepository;
import engine.service.BasicQuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/quizzes")
public class QuizController {
    private static final  Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private BasicQuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public Quiz addQuiz(@Valid @RequestBody final Quiz quiz) {
        LOGGER.info("The quiz is "+ quiz.toString() + "with answer" + quiz.getAnswer());

        LOGGER.info("The output will be " + quizService.toString());
        return quizService.saveQuiz(quiz);

    }

    @GetMapping(path = "/{id}")
    public Quiz getQuizWithId(@PathVariable final long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping(path = "/{id}/solve", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response solveQuiz(@PathVariable final long id, @RequestBody Answer answer) {
        Optional<Quiz> quizzes = quizRepository.findById(id);
        if (quizzes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Quiz quiz = quizzes.get();
        if (Arrays.equals(quiz.getAnswer(),answer.getAnswer()))
                 return  Response.CORRECT;
        else return Response.INCORRECT;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id, @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            if (quiz.get().getAuthor().equals(user)) {
                quizRepository.delete(quiz.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}

