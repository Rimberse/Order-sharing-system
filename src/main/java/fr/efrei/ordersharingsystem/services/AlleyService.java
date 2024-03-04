package fr.efrei.ordersharingsystem.services;

import fr.efrei.ordersharingsystem.domain.Alley;
import fr.efrei.ordersharingsystem.repositories.AlleyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlleyService {
    private AlleyRepository alleyRepository;

    @Autowired
    public AlleyService(final AlleyRepository alleyRepository) {
        this.alleyRepository = alleyRepository;
    }

    public List<Alley> findAll() {
        return alleyRepository.findAll();
    }

    public List<Alley> findAllByNumber(int number) {
        return alleyRepository.findAllByNumber(number);
    }

    public List<Alley> findAllByBowlingPark(Long parkId) {
        return alleyRepository.findAlleyByParkId(parkId);
    }

    public Alley findByNumberAndBowlingPark(int number, Long parkId) {
        Alley alley = alleyRepository.findByNumberAndParkId(number, parkId).orElse(null);

        if (alley != null) {
            return alley;
        } else {
            throw new IllegalArgumentException("No alley has been found with the given number: " + number + " and bowling park: " + parkId);
        }
    }

    public List<Alley> save(Iterable<Alley> alleys) {
        return alleyRepository.save(alleys);
    }

    public Alley save(Alley alley) {
        return alleyRepository.save(alley);
    }

    boolean existsByNumber(int number) {
        return alleyRepository.existsByNumber(number);
    }

    boolean existsByBowlingPark(Long parkId) {
        return alleyRepository.existsByParkId(parkId);
    }
}
