package com.example.BookReview.business.service.implementation;

import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.base.BookReview;
import com.example.BookReview.business.model.create.BookReviewCreateModel;
import com.example.BookReview.business.service.interfaces.BookReviewService;
import com.example.BookReview.data.model.BookDB;
import com.example.BookReview.data.model.BookReviewDB;
import com.example.BookReview.data.model.ReaderDB;
import com.example.BookReview.data.repository.BookRepository;
import com.example.BookReview.data.repository.BookReviewRepository;
import com.example.BookReview.data.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.processing.SupportedOptions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookReviewServiceImplementation implements BookReviewService {


    @Qualifier("bookReviewRepository")
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Qualifier("bookRepository")
    @Autowired
    private BookRepository bookRepository;
    @Qualifier("readerRepository")
    @Autowired
    private ReaderRepository readerRepository;


    @Override
    public List<BookReview> findAll() {
        return bookReviewRepository.findAll().stream().map(BookReview::new).collect(Collectors.toList());
    }

    @Override
    public BookReview findById(Long id) {
        Optional<BookReviewDB> bookReviewDB = bookReviewRepository.findById(id);
        return bookReviewDB.map(BookReview::new).orElse(null);
    }

    @Override
    public List<BookReview> findAllByBook_Id(Long book_id) {
        return bookReviewRepository.findAllByBook_Id(book_id).stream().map(BookReview::new).collect(Collectors.toList());
    }

    @Override
    public List<BookReview> findAllByReader_Id(Long reader_id) {
        return bookReviewRepository.findAllByReader_Id(reader_id).stream().map(BookReview::new).collect(Collectors.toList());
    }

    @Override
    public BookReview create(BookReviewCreateModel createModel) {
        Optional<BookDB> book = bookRepository.findById(createModel.getBookId());
        if (book.isEmpty()) {
            return null;
        }

        Optional<ReaderDB> reader = readerRepository.findById(createModel.getReaderId());
        if (reader.isEmpty()) {
            return null;
        }

        BookReviewDB review = new BookReviewDB(
                createModel.getReview(),
                book.get(),
                reader.get()
        );

        BookReviewDB saved = bookReviewRepository.save(review);

        return new BookReview(saved);
    }

    @Override
    public BookReview update(Long id, BookReviewCreateModel newValue) {
        Optional<BookReviewDB> bookReviewDB = bookReviewRepository.findById(id);
        if (bookReviewDB.isEmpty()) {
            return null;
        }

        BookReviewDB toUpdate = bookReviewDB.get();

        Optional<BookDB> book = bookRepository.findById(newValue.getBookId());
        book.ifPresent(toUpdate::setBook);

        Optional<ReaderDB> reader = readerRepository.findById(newValue.getReaderId());
        reader.ifPresent(toUpdate::setReader);

        if(newValue.getReview() != null){
            toUpdate.setReview(newValue.getReview());
        }

        BookReviewDB updated = bookReviewRepository.save(toUpdate);

        return new BookReview(updated);
    }

    @Override
    public BookReview deleteById(Long id) {
        Optional<BookReviewDB> bookReviewDB = bookReviewRepository.findById(id);
        if (bookReviewDB.isEmpty()) {
            return null;
        }

        BookReviewDB toDelete = bookReviewDB.get();

        bookReviewRepository.deleteById(id);
        return new BookReview(toDelete);
    }
}
