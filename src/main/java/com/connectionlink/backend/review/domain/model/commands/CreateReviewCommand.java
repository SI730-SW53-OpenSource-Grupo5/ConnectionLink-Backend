package com.connectionlink.backend.review.domain.model.commands;

public record CreateReviewCommand(String description, String specialistUsername, String userUsername) {

}