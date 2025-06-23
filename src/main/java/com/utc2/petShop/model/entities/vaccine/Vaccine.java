package com.utc2.petShop.model.entities.vaccine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Vaccine {
    private IntegerProperty vaccineId;
    private StringProperty vaccineName;
    private StringProperty description;
    private StringProperty applicableSpecies;
    private IntegerProperty doseCount;
    private IntegerProperty intervalDays;
    private IntegerProperty validityMonths;
    private BooleanProperty isMandatory;

    public int getVaccineId() {
        return vaccineId.get();
    }

    public IntegerProperty vaccineIdProperty() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId.set(vaccineId);
    }

    public java.lang.String getVaccineName() {
        return vaccineName.get();
    }

    public StringProperty vaccineNameProperty() {
        return vaccineName;
    }

    public void setVaccineName(java.lang.String vaccineName) {
        this.vaccineName.set(vaccineName);
    }

    public java.lang.String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description.set(description);
    }

    public java.lang.String getApplicableSpecies() {
        return applicableSpecies.get();
    }

    public StringProperty applicableSpeciesProperty() {
        return applicableSpecies;
    }

    public void setApplicableSpecies(java.lang.String applicableSpecies) {
        this.applicableSpecies.set(applicableSpecies);
    }

    public int getDoseCount() {
        return doseCount.get();
    }

    public IntegerProperty doseCountProperty() {
        return doseCount;
    }

    public void setDoseCount(int doseCount) {
        this.doseCount.set(doseCount);
    }

    public int getIntervalDays() {
        return intervalDays.get();
    }

    public IntegerProperty intervalDaysProperty() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays.set(intervalDays);
    }

    public int getValidityMonths() {
        return validityMonths.get();
    }

    public IntegerProperty validityMonthsProperty() {
        return validityMonths;
    }

    public void setValidityMonths(int validityMonths) {
        this.validityMonths.set(validityMonths);
    }

    public boolean isIsMandatory() {
        return isMandatory.get();
    }

    public BooleanProperty isMandatoryProperty() {
        return isMandatory;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory.set(isMandatory);
    }

    public Vaccine(IntegerProperty vaccineId, StringProperty vaccineName, StringProperty description, StringProperty applicableSpecies, IntegerProperty doseCount, IntegerProperty intervalDays, IntegerProperty validityMonths, BooleanProperty isMandatory) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.description = description;
        this.applicableSpecies = applicableSpecies;
        this.doseCount = doseCount;
        this.intervalDays = intervalDays;
        this.validityMonths = validityMonths;
        this.isMandatory = isMandatory;
    }

    public Vaccine() {
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Vaccine{" +
                "vaccineId=" + vaccineId +
                ", vaccineName=" + vaccineName +
                ", description=" + description +
                ", applicableSpecies=" + applicableSpecies +
                ", doseCount=" + doseCount +
                ", intervalDays=" + intervalDays +
                ", validityMonths=" + validityMonths +
                ", isMandatory=" + isMandatory +
                '}';
    }
}
