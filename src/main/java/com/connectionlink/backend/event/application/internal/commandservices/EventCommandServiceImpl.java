package com.connectionlink.backend.event.application.internal.commandservices;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.infrastructure.persitence.jpa.CategoryRepository;
import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.commands.AddUserCommand;
import com.connectionlink.backend.event.domain.model.commands.CreateEventCommand;
import com.connectionlink.backend.event.domain.model.commands.RemoveUserCommand;
import com.connectionlink.backend.event.domain.model.commands.UpdateEventCommand;
import com.connectionlink.backend.event.domain.services.EventCommandService;
import com.connectionlink.backend.event.infrastructure.persitence.jpa.EventRepository;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public EventCommandServiceImpl(EventRepository eventRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        Category category = this.categoryRepository.findById(command.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        User user = this.userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Event event = new Event(command, category, user);

        var eventSaved = this.eventRepository.save(event);
        return Optional.of(eventSaved);
    }

    @Override
    public Optional<Event> handle(AddUserCommand command) {
        User user = this.userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = this.eventRepository.findById(command.eventId()).orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if(!event.getUsers().contains(user)) {
            event.getUsers().add(user);

            var eventUpdated = this.eventRepository.save(event);
            return Optional.of(eventUpdated);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Event> handle(RemoveUserCommand command) {
        User user = this.userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = this.eventRepository.findById(command.eventId()).orElseThrow(() -> new IllegalArgumentException("Event not found"));


        if(event.getUsers().contains(user)) {
            event.getUsers().remove(user);

            var eventUpdated = this.eventRepository.save(event);
            return Optional.of(eventUpdated);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Event> handle(UpdateEventCommand command, Long id) {
        User user = this.userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Category category = this.categoryRepository.findById(command.categoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Event event = this.eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found"));

        event.setSpecialist(user);
        event.setCategory(category);

        event.setTitle(command.title());
        event.setDescription(command.description());
        event.setDay(command.day());
        event.setProfileImageUrl(command.profileImageUrl());
        event.setBannerImageUrl(command.bannerImageUrl());

        var eventUploaded = this.eventRepository.save(event);

        return Optional.of(eventUploaded);
    }
}
