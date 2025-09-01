package com.exhio_api.exhio_api.domain;

import com.exhio_api.exhio_api.dto.monster.CreateMonsterDTO;
import com.exhio_api.exhio_api.dto.monster.UpdateMonsterDTO;
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

@SpringBootTest
class MonsterTest {

    private Monster monster;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        monster = new Monster(
                1L,
                1,
                "Sapo",
                23,
                new Resistances(0,0,-20,20,40,30,20),
                new HashSet<>(),
                false
        );
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should initialize with correct values")
    public void shouldInitializeWithCorrectValues() {
        assertAll(
                () -> assertEquals(monster.getId(), 1L),
                () -> assertEquals(monster.getLevel(), 1),
                () -> assertEquals(monster.getName(), "Sapo"),
                () -> assertEquals(monster.getExperience(), 23),
                () -> assertEquals(monster.getDeleted(), false),
                () -> assertEquals(monster.getResistances().getPhysical(), 0),
                () -> assertEquals(monster.getResistances().getFire(), 0),
                () -> assertEquals(monster.getResistances().getEnergy(), -20),
                () -> assertEquals(monster.getResistances().getIce(), 20),
                () -> assertEquals(monster.getResistances().getPoison(), 40),
                () -> assertEquals(monster.getResistances().getDeath(), 30),
                () -> assertEquals(monster.getResistances().getHoly(), 20)
        );
    }

    @Test
    @DisplayName("Should have one violation for null level")
    public void shouldHaveOneViolationForNullLevel() {
        monster.setLevel(null);
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for negative level")
    public void shouldHaveOneViolationForNegativeLevel() {
        monster.setLevel(-15);
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for null name")
    public void shouldHaveOneViolationForNullName() {
        monster.setName(null);
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for blank name")
    public void shouldHaveOneViolationForBlankName() {
        monster.setName("");
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation for null experience")
    public void shouldHaveOneViolationForNullExperience() {
        monster.setExperience(null);
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violator for negative experience")
    public void shouldHaveOneViolationForNegativeExperience() {
        monster.setExperience(-15);
        Set<ConstraintViolation<Monster>> violations = validator.validate(monster);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Should have one violation if any resistance is higher than 100")
    public void shouldHaveOneViolationIfAnyResistanceIsHigherThanOneHundred() {
        monster.setResistances(
                new Resistances(
                        101,101,101,101,101,101,101
                )
        );
        Set<ConstraintViolation<Resistances>> violations = validator.validate(monster.getResistances());
        assertEquals(7, violations.size());
    }

    @Test
    @DisplayName("Should change name successfully")
    public void shouldChangeNameSuccessfully() {
        monster.setName("Arkabuz");
        assertEquals("Arkabuz", monster.getName());
    }

    @Test
    @DisplayName("Should change experience successfully")
    public void shouldChangeExperienceSuccessfully() {
        monster.setExperience(1300);
        assertEquals(1300, monster.getExperience());
    }

    @Test
    @DisplayName("Should change level successfully")
    public void shouldChangeLevelSuccessfully() {
        monster.setLevel(15);
        assertEquals(15, monster.getLevel());
    }

    @Test
    @DisplayName("Should update successfully")
    public void shouldUpdateSuccessfully() {
        UpdateMonsterDTO dto = new UpdateMonsterDTO(
                "Aberracao",
                92,
                13200,
                new Resistances(
                        50, 50, 50, 50, 50, 50, 50
                )
        );
        monster.updateData(dto);
        assertEquals(dto.name(), monster.getName());
        assertEquals(dto.level(), monster.getLevel());
        assertEquals(dto.experience(), monster.getExperience());
        assertEquals(dto.resistances(), monster.getResistances());
    }

    @Test
    @DisplayName("Should update successfully with null dto")
    public void shouldUpdateSuccessfullyWithNulLDTO() {
        UpdateMonsterDTO dto = new UpdateMonsterDTO(
                null,
                null,
                null,
                null
                );
        monster.updateData(dto);

        assertEquals("Sapo", monster.getName());
        assertEquals(1L, monster.getId());
        assertEquals(23, monster.getExperience());
        assertEquals(new Resistances(0,0,-20,20,40,30,20), monster.getResistances());
        assertEquals(false, monster.getDeleted());
    }

    @Test
    @DisplayName("Should update successfully with null resistances")
    public void shouldUpdateSuccessfullyWithNullResistances() {
        UpdateMonsterDTO dto = new UpdateMonsterDTO(
                null,
                null,
                null,
                new Resistances(null, null, null, null, null, null, null)
        );

        monster.updateData(dto);
        assertEquals("Sapo", monster.getName());
        assertEquals(1L, monster.getId());
        assertEquals(23, monster.getExperience());
        assertEquals(new Resistances(0,0,-20,20,40,30,20), monster.getResistances());
        assertEquals(false, monster.getDeleted());
    }

    @Test
    @DisplayName("Should create successfully")
    public void shouldCreateSuccessfully() {
        CreateMonsterDTO dto = new CreateMonsterDTO(
                "Aberracao",
                92,
                13200,
                new Resistances(
                        50, 50, 50, 50, 50, 50, 50
                )
        );
        Monster monster = new Monster(dto);
        assertEquals(dto.name(), monster.getName());
        assertEquals(dto.level(), monster.getLevel());
        assertEquals(dto.experience(), monster.getExperience());
        assertEquals(dto.resistances(), monster.getResistances());
        assertFalse(monster.getDeleted());
    }

}