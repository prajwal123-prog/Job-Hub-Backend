
package com.jobhub.jobhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ApplicationRequestDto {

    @NotNull(message = "Job ID is required")
    @Positive(message = "Job ID must be a positive number")
    private Long jobId;

    @NotBlank(message = "Resume URL is required")
    @Size(max = 2048, message = "Resume URL must not exceed 2048 characters")
    @Pattern(
        regexp = "^(https?://\\S+|data:application/pdf;base64,[A-Za-z0-9+/=\\r\\n]+)$",
        message = "Resume must be a valid HTTP/HTTPS URL or a PDF data URL"
    )
    private String resumeUrl;

    @Size(max = 5000, message = "Cover letter must not exceed 5000 characters")
    private String coverLetter;

    public ApplicationRequestDto() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
