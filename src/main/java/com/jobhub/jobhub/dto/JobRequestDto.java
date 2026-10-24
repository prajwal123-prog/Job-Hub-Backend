
package com.jobhub.jobhub.dto;

import com.jobhub.jobhub.entity.JobType;
import com.jobhub.jobhub.validation.ValidSalaryRange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@ValidSalaryRange
public class JobRequestDto {

    @NotBlank(message = "Job title is required")
    @Size(max = 150, message = "Job title cannot exceed 150 characters")
    private String title;

    @NotBlank(message = "Company name is required")
    @Size(max = 150, message = "Company name cannot exceed 150 characters")
    private String companyName;

    @NotBlank(message = "Job description is required")
    @Size(max = 10000, message = "Description cannot exceed 10000 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 150, message = "Location cannot exceed 150 characters")
    private String location;

    @NotNull(message = "Job type is required (FULL_TIME, PART_TIME, INTERNSHIP, CONTRACT)")
    private JobType jobType;

    @Size(max = 100, message = "Experience level cannot exceed 100 characters")
    private String experienceLevel;

    @PositiveOrZero(message = "Minimum salary cannot be negative")
    private Double salaryMin;

    @PositiveOrZero(message = "Maximum salary cannot be negative")
    private Double salaryMax;

    @NotBlank(message = "Required skills must be specified")
    @Size(max = 2000, message = "Skills cannot exceed 2000 characters")
    private String skills;

    public JobRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
