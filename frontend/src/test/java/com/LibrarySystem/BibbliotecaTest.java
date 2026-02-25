package com.LibrarySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BibbliotecaTest {

    private Bibblioteca biblioteca;
    private Utente utente1;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setup() {
        // Creo la biblioteca
        biblioteca = new Bibblioteca();

        // Creo libri
        libro1 = new Libro("Il Signore degli Anelli", "Tolkien", 1954, true);
        libro2 = new Libro("1984", "Orwell", 1949, true);

        biblioteca.aggiungereLibro(libro1);
        biblioteca.aggiungereLibro(libro2);

        // Creo utente
        utente1 = new Utente("Mario Rossi",1);
        biblioteca.registraUtente(utente1);
    }

    @Test
    void testLibriDisponibili() {
        // All'inizio tutti i libri sono disponibili
        assertEquals(2, biblioteca.libriDisponibili().size());
    }

    @Test
    void testPrestitoLibro() {
        // Presta libro1 all'utente1
        Libro prestito = biblioteca.prestitoLibro(1, "Il Signore degli Anelli");
        assertNotNull(prestito);
        assertFalse(prestito.isDisponibile());
        assertEquals(1, utente1.getLibriPrestito().size());

        // Dopo il prestito, libri disponibili diminuiscono
        assertEquals(1, biblioteca.libriDisponibili().size());
    }

    @Test
    void testPrestitoLibroNonDisponibile() {
        // Presto libro1 due volte
        biblioteca.prestitoLibro(1, "Il Signore degli Anelli");
        Libro secondoPrestito = biblioteca.prestitoLibro(1, "Il Signore degli Anelli");

        assertNull(secondoPrestito); // non disponibile
    }

    @Test
    void testRestituzioneLibro() {
        // Presto e poi restituisco libro1
        biblioteca.prestitoLibro(1, "Il Signore degli Anelli");
        biblioteca.restituzioneLibro(1, "Il Signore degli Anelli");

        // Il libro torna disponibile
        assertTrue(libro1.isDisponibile());
        assertEquals(0, utente1.getLibriPrestito().size());
    }

    @Test
    void testPrestitoLibroUtenteNonEsistente() {
        // Utente inesistente
        Libro prestito = biblioteca.prestitoLibro(999, "1984");
        assertNull(prestito);
    }

    @Test
    void testRestituzioneLibroNonPrestato() {
        // Provo a restituire un libro mai preso
        biblioteca.restituzioneLibro(1, "1984"); // non l'ha preso
        // Nessun errore, il libro rimane disponibile
        assertTrue(libro2.isDisponibile());
    }
}
