package com.mc.MCe.service;

import com.mc.MCe.dao.CastObceDAO;
import com.mc.MCe.dao.ObecDAO;
import com.mc.MCe.entity.CastObce;
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
class CastObceServiceImplTest {
    @Mock private CastObceDAO castObceDAO;
    @Mock private ObecDAO obceDAO;
    private CastObceService underTest;

    @BeforeEach
    void setUp(){
        underTest = new CastObceServiceImpl(obceDAO, castObceDAO);
    }

    @Test
    void canGetAllCastObce(){
        //when
        underTest.getAllCastiObce();
        //then
        verify(castObceDAO).getAllCastiObce();
    }

    @Test
    void canSaveCastObce(){
        CastObce castObce = CastObce.createCastObce(1,"name", 1,true);
        //when
        underTest.addCastObce(castObce);
        //then
        ArgumentCaptor<CastObce> castObceArgumentCaptor = ArgumentCaptor.forClass(CastObce.class);

        verify(castObceDAO).addCastObce(castObceArgumentCaptor.capture());

        CastObce capturedCastObce = castObceArgumentCaptor.getValue();

        assertThat(capturedCastObce).isEqualTo(castObce);
    }

    @Test
    void canGetCastObceById(){
        //when
        underTest.getCastObceByCode(1);
        //then
        verify(castObceDAO).getCastObceByCode(1);
    }

}