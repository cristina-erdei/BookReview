package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.BookRating;
import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.create.BookRatingCreateModel;
import com.example.BookReview.business.model.create.BookReviewCreateModel;
import com.example.BookReview.business.service.interfaces.BookRatingService;
import com.example.BookReview.data.model.BookDB;
import com.example.BookReview.data.model.BookRatingDB;
import com.example.BookReview.data.model.BookReviewDB;
import com.example.BookReview.data.model.ReaderDB;
import com.example.BookReview.data.repository.BookRatingRepository;
import com.example.BookReview.data.repository.BookRepository;
import com.example.BookReview.data.repository.BookReviewRepository;
import com.example.BookReview.data.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookRatingServiceImplementation implements BookRatingService {

    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;
    @Qualifier("readerRepository")
    @Autowired
    private ReaderRepository readerRepository;
    @Qualifier("bookRatingRepository")
    @Autowired
    private BookRatingRepository bookRatingRepository;


    @Override
    public List<BookRating> findAll() {
        return bookRatingRepository.findAll().stream().map(BookRating::new).collect(Collectors.toList());
    }

    @Override
    public BookRating findById(Long id) {
        Optional<BookRatingDB> bookRatingDB = bookRatingRepository.findById(id);
        return bookRatingDB.map(BookRating::new).orElse(null);
    }

    @Override
    public List<BookRating> findAllByBook_Id(Long book_id) {
        return bookRatingRepository.findAllByBook_Id(book_id).stream().map(BookRating::new).collect(Collectors.toList());
    }

    @Override
    public List<BookRating> findAllByReader_Id(Long reader_id) {
        return bookRatingRepository.findAllByReader_Id(reader_id).stream().map(BookRating::new).collect(Collectors.toList());
    }

    @Override
    public BookRating create(BookRatingCreateModel createModel) {
        Optional<BookDB> book = bookRepository.findById(createModel.getBookId());
        if (book.isEmpty()) {
            return null;
        }

        Optional<ReaderDB> reader = readerRepository.findById(createModel.getReaderId());
        if (reader.isEmpty()) {
            return null;
        }

        BookRatingDB review = new BookRatingDB(
                createModel.getRating(),
                book.get(),
                reader.get()
        );

        BookRatingDB saved = bookRatingRepository.save(review);

        return new BookRating(saved);
    }

    @Override
    public BookRating update(Long id, BookRatingCreateModel newValue) {
        Optional<BookRatingDB> bookRatingDB = bookRatingRepository.findById(id);
        if (bookRatingDB.isEmpty()) {
            return null;
        }

        BookRatingDB toUpdate = bookRatingDB.get();

        Optional<BookDB> book = bookRepository.findById(newValue.getBookId());
        book.ifPresent(toUpdate::setBook);

        Optional<ReaderDB> reader = readerRepository.findById(newValue.getReaderId());
        reader.ifPresent(toUpdate::setReader);

        if(newValue.getRating() > 0){
            toUpdate.setRating(newValue.getRating());
        }

        BookRatingDB updated = bookRatingRepository.save(toUpdate);

        return new BookRating(updated);
    }

    @Override
    public BookRating deleteById(Long id) {
        Optional<BookRatingDB> bookRatingDB = bookRatingRepository.findById(id);
        if (bookRatingDB.isEmpty()) {
            return null;
        }

        BookRatingDB toDelete = bookRatingDB.get();

        bookRatingRepository.deleteById(id);
        return new BookRating(toDelete);
    }
}
