package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatistiqueTests {

    @Test
    public void testPrixMoyenAvecDeuxVoitures() {
        Statistique statistique = new StatistiqueImpl();

        Voiture voiture1 = mock(Voiture.class);
        Voiture voiture2 = mock(Voiture.class);

        when(voiture1.getPrix()).thenReturn(10000);
        when(voiture2.getPrix()).thenReturn(20000);

        statistique.ajouter(voiture1);
        statistique.ajouter(voiture2);

        Echantillon resultat = statistique.prixMoyen();

        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(15000, resultat.getPrixMoyen());

        verify(voiture1).getPrix();
        verify(voiture2).getPrix();
    }

    @Test
    public void testPrixMoyenSansVoiture() {
        Statistique statistique = new StatistiqueImpl();

        assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        });
    }
}