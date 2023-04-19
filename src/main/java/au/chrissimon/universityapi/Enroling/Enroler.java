package au.chrissimon.universityapi.Enroling;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

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

    public Optional<Enrolment> enrolIfEnoughCapacity(UUID studentId, Enrolment enrolment) {
        return roomRepository.findForEnrolment(enrolment)
            .flatMap(room -> {
                long numEnrolments = enrolmentRepository.countByCourseId(enrolment.getCourseId());

                if (room.wouldEnrolmentExceedCapacity(numEnrolments)) {
                    return Optional.empty();
                }        

                return Optional.of(new Enrolment(UUID.randomUUID(), studentId, enrolment.getCourseId()));
            });
    }
}
