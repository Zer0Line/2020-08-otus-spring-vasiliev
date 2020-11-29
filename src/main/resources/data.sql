-- authors
INSERT INTO AUTHORS (ID, `NAME`) VALUES (1, 'Brian Goetz');
INSERT INTO AUTHORS (ID, `NAME`) VALUES (2, 'Maxim Dorofeev');

-- genres
INSERT INTO GENRES (ID, `GENRE`) VALUES (1, 'computer science');
INSERT INTO GENRES (ID, `GENRE`) VALUES (2, 'personal effectiveness');

-- books
INSERT INTO BOOKS (ID, AUTHOR_ID, GENRE_ID, `NAME`) VALUES (1, 1, 1, 'Java Concurrency in Practice');
INSERT INTO BOOKS (ID, AUTHOR_ID, GENRE_ID, `NAME`) VALUES (2, 2, 2, 'Jedi Techniques');