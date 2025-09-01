package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterIdDTO;
import com.exhio_api.exhio_api.dto.hunt.CreateHuntByMonsterNameDTO;
import com.exhio_api.exhio_api.dto.hunt.UpdateHuntDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HuntTest {

    private Hunt hunt;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.hunt = new Hunt(
            1L,
            "Lar dos Dragões",
            "35+",
            "Tem muitos dragões",
            new HashSet<>(),
            "www.youtube.com.br",
            false,
            false
        );
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    @Test
    @DisplayName("Should initialize with correct values")
    public void shouldInitializeWithCorrectValues() {
        assertAll(
                () -> assertEquals(1L, hunt.getId()),
                () -> assertEquals("Lar dos Dragões", hunt.getName()),
                () -> assertEquals("35+", hunt.getRecommendedLevel()),
                () -> assertEquals("Tem muitos dragões", hunt.getDescription()),
                () -> assertEquals(0, hunt.getMonsters().size()),
                () -> assertTrue(hunt.getMonsters().isEmpty()),
                () -> assertEquals("www.youtube.com.br", hunt.getVideoURL()),
                () -> assertFalse(hunt.getPremium()),
                () -> assertFalse(hunt.getDeleted())
        );
    }

    @Test
    @DisplayName("Should have one violation for null name")
    public void shouldHaveOneViolationForNullName() {
        hunt.setName(null);
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for blank name")
    public void shouldHaveOneViolationForBlankName() {
        hunt.setName("");
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for null recommended level")
    public void shouldHaveOneViolationForNullRecommendedLevel() {
        hunt.setRecommendedLevel(null);
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for blank recommended level")
    public void shouldHaveOneViolationForBlankRecommendedLevel() {
        hunt.setRecommendedLevel("");
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for null description")
    public void shouldHaveOneViolationForNullDescription() {
        hunt.setDescription(null);
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for blank description")
    public void shouldHaveOneViolationForBlankDescription() {
        hunt.setDescription("");
        Set<ConstraintViolation<Hunt>> violations = validator.validate(hunt);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should add a monster successfully")
    public void shouldAddAMonsterSuccessfully() {
        Monster monster = new Monster();
        hunt.getMonsters().add(monster);
        assertEquals(1, hunt.getMonsters().size());
        assertTrue(hunt.getMonsters().contains(monster));
    }

    @Test
    @DisplayName("Should update successfully with null dto")
    public void shouldUpdateSuccessfullyWithNullDTO() {
        UpdateHuntDTO huntDTO = new UpdateHuntDTO(
                null,
                null,
                null,
                null,
                null,
                null
        );
        hunt.updateData(huntDTO);
        assertAll(
                () -> assertEquals(1L, hunt.getId()),
                () -> assertEquals("Lar dos Dragões", hunt.getName()),
                () -> assertEquals("35+", hunt.getRecommendedLevel()),
                () -> assertEquals("Tem muitos dragões", hunt.getDescription()),
                () -> assertEquals(0, hunt.getMonsters().size()),
                () -> assertTrue(hunt.getMonsters().isEmpty()),
                () -> assertEquals("www.youtube.com.br", hunt.getVideoURL()),
                () -> assertFalse(hunt.getPremium()),
                () -> assertFalse(hunt.getDeleted())
        );
    }

    @Test
    @DisplayName("Should update successfully with full dto")
    public void shouldUpdateSuccessfullyWIthFulLDTO() {
        Set<String> monsterSet = new HashSet<>();
        UpdateHuntDTO huntDTO = new UpdateHuntDTO(
                "Teste",
                "Uma hunt de teste",
                "35+",
                "www.youtube.com.br",
                true,
                monsterSet
        );
        hunt.updateData(huntDTO);
        assertAll(
                () -> assertEquals(1L, hunt.getId()),
                () -> assertEquals("Teste", hunt.getName()),
                () -> assertEquals("35+", hunt.getRecommendedLevel()),
                () -> assertEquals("Uma hunt de teste", hunt.getDescription()),
                () -> assertEquals(0, hunt.getMonsters().size()),
                () -> assertTrue(hunt.getMonsters().isEmpty()),
                () -> assertEquals("www.youtube.com.br", hunt.getVideoURL()),
                () -> assertTrue(hunt.getPremium()),
                () -> assertFalse(hunt.getDeleted())
        );
    }

    @Test
    @DisplayName("Should create successfully with CreateHuntByMonsterIdDTO")
    public void shouldCreateSuccessfullyWithCreateHuntByMonsterIdDTO() {
        CreateHuntByMonsterIdDTO huntDto = new CreateHuntByMonsterIdDTO(
                "Teste",
                "Teste Description",
                "35+",
                null,
                false,
                new HashSet<>()
        );
        Hunt hunt = new Hunt(huntDto);
        assertAll(
                () -> assertEquals(huntDto.name(), hunt.getName()),
                () -> assertEquals(huntDto.description(), hunt.getDescription()),
                () -> assertEquals(huntDto.recommendedLevel(), hunt.getRecommendedLevel()),
                () -> assertNull(hunt.getVideoURL()),
                () -> assertFalse(hunt.getPremium()),
                () -> assertEquals(0, hunt.getMonsters().size()),
                () -> assertTrue(hunt.getMonsters().isEmpty())
        );
    }

    @Test
    @DisplayName("Should create successfully with CreateHuntByMonsterNameDTO")
    public void shouldCreateSuccessfullyWithCreateHuntByMonsterNameDTO() {
        CreateHuntByMonsterNameDTO huntDto = new CreateHuntByMonsterNameDTO(
                "Teste",
                "Teste Description",
                "35+",
                null,
                false,
                new HashSet<>()
        );
        Hunt hunt = new Hunt(huntDto);
        assertAll(
                () -> assertEquals(huntDto.name(), hunt.getName()),
                () -> assertEquals(huntDto.description(), hunt.getDescription()),
                () -> assertEquals(huntDto.recommendedLevel(), hunt.getRecommendedLevel()),
                () -> assertNull(hunt.getVideoURL()),
                () -> assertFalse(hunt.getPremium()),
                () -> assertEquals(0, hunt.getMonsters().size()),
                () -> assertTrue(hunt.getMonsters().isEmpty())
        );
    }

}