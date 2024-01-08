package com.mc.MCe.service;

import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.Obec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ObecServiceImplTest {

    @Mock private ObecDAO obceDAO;

    private ObecService underTest;

    @BeforeEach
    void setUp(){
        underTest = new ObecServiceImpl(obceDAO);
    }

    @Test
    void canAddObec() {
        Obec obec = Obec.createObec("obec", 1, true);
        underTest.addObec(obec);

        ArgumentCaptor<Obec> obecArgumentCaptor = ArgumentCaptor.forClass(Obec.class);
        verify(obceDAO).addObec(obecArgumentCaptor.capture());

        Obec capturedObec = obecArgumentCaptor.getValue();

        assertThat(capturedObec).isEqualTo(obec);
    }

    @Test
    void canGetAllObce() {
        underTest.getAllObce();
        verify(obceDAO).getAllObce();
    }

    @Test
    void canGetObecByCode() {
        underTest.getObecByCode(1);
        verify(obceDAO).getObecByCode(1);
    }
}