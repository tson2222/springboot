//package com.wk.autoverleihMVC.service;
//
//import com.wk.autoverleihMVC.model.Car;
//import com.wk.autoverleihMVC.repository.CarRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service("rideService")
//public class RideServiceImpl implements RideService {
//
//	@Autowired
//	private RideRepository rideRepository;
//
//	@Override
//	public Ride createRide(Ride ride) {
//		return rideRepository.createRide(ride);
//	}
//
//	@Override
//	public Ride getRide(Integer id) {
//		return rideRepository.getRide(id);
//	}
//
//	@Override
//	public List<Ride> getRides() {
//		return rideRepository.getRides();
//	}
//
//	@Override
//	public Ride updateRide(Ride ride) {
//		return rideRepository.updateRide(ride);
//	}
//
//	@Override
//	@Transactional
//	public void batch() {
//		List<Ride> rides = rideRepository.getRides();
//
//		List<Object[]> pairs = new ArrayList<>();
//
//		for (Ride ride : rides) {
//			Object [] tmp = {new Date(), ride.getId()};
//			pairs.add(tmp);
//		}
//
//		rideRepository.updateRides(pairs);
//
//		//throw new DataAccessException("Testing Exception Handling") {
//		//};
//	}
//
//	@Override
//	public void deleteRide(Integer id) {
//		rideRepository.deleteRide(id);
//	}
//
//}
