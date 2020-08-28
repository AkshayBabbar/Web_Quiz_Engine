package engine.service;

import engine.entity.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BasicQuizService  {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizRepository.findAll();
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Quiz with id {%d} could not be found.", id)));
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}