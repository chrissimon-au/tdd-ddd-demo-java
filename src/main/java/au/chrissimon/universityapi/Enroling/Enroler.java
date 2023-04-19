package au.chrissimon.universityapi.Enroling;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import au.chrissimon.universityapi.Rooms.Room;
import au.chrissimon.universityapi.Rooms.RoomRepository;

@Component
public class Enroler {

    private RoomRepository roomRepository;
    private EnrolmentRepository enrolmentRepository;

    public Enroler(RoomRepository roomRepository,
        EnrolmentRepository enrolmentRepository
    ) {
        super();
        this.roomRepository = roomRepository;
        this.enrolmentRepository = enrolmentRepository;
    }

    private Function<Room, Optional<Room>> checkRoomCapacity(UUID courseId) {
        return room -> {
            long numEnrolments = enrolmentRepository.countByCourseId(courseId);

            return room.roomIfCapacityForEnrolment(numEnrolments);
        };
    }

    private Function<Room, Optional<Enrolment>> enrol(UUID studentId, UUID courseId) {
        return room -> Optional.of(new Enrolment(UUID.randomUUID(), studentId, courseId));
    }

    public Optional<Enrolment> enrolIfEnoughCapacity(UUID studentId, Enrolment enrolment) {
        return roomRepository.findForEnrolment(enrolment)
            .flatMap(checkRoomCapacity(enrolment.getCourseId()))
            .flatMap(enrol(studentId, enrolment.getCourseId()));
    }
}
