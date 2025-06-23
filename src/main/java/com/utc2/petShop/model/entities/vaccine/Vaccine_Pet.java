package com.utc2.petShop.model.entities.vaccine;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Vaccine_Pet {
    private IntegerProperty petId;
    private IntegerProperty vaccineId;
    private ObjectProperty<LocalDate> vaccinationDate;
    private IntegerProperty doseNumber;

    public Vaccine_Pet() {
        this.petId = new SimpleIntegerProperty();
        this.vaccineId = new SimpleIntegerProperty();
        this.vaccinationDate = new SimpleObjectProperty<>();
        this.doseNumber = new SimpleIntegerProperty();
    }

    public Vaccine_Pet(int petId, int vaccineId, LocalDate vaccinationDate, int doseNumber) {
        this.petId = new SimpleIntegerProperty(petId);
        this.vaccineId = new SimpleIntegerProperty(vaccineId);
        this.vaccinationDate = new SimpleObjectProperty<>(vaccinationDate);
        this.doseNumber = new SimpleIntegerProperty(doseNumber);
    }

    public int getPetId() {
        return petId.get();
    }

    public void setPetId(int petId) {
        this.petId.set(petId);
    }

    public IntegerProperty petIdProperty() {
        return petId;
    }

    public int getVaccineId() {
        return vaccineId.get();
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId.set(vaccineId);
    }

    public IntegerProperty vaccineIdProperty() {
        return vaccineId;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate.get();
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate.set(vaccinationDate);
    }

    public ObjectProperty<LocalDate> vaccinationDateProperty() {
        return vaccinationDate;
    }

    public int getDoseNumber() {
        return doseNumber.get();
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber.set(doseNumber);
    }

    public IntegerProperty doseNumberProperty() {
        return doseNumber;
    }

    @Override
    public String toString() {
        return "Vaccine_Pet{" +
                "petId=" + petId.get() +
                ", vaccineId=" + vaccineId.get() +
                ", vaccinationDate=" + vaccinationDate.get() +
                ", doseNumber=" + doseNumber.get() +
                '}';
    }
}
