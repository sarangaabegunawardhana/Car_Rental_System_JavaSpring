package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (!driverRepo.existsById(driverDTO.getDriverId())) {
            driverRepo.save(modelMapper.map(driverDTO, Driver.class));
        } else {
            throw new RuntimeException("Driver Already Exist..!");
        }
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) {
        if (driverRepo.existsById(driverDTO.getDriverId())) {
            driverRepo.save(modelMapper.map(driverDTO,Driver.class));
        } else {
            throw new RuntimeException("No Such Driver To Update..! Please Check the ID..!");
        }
    }

    @Override
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)){
            driverRepo.deleteById(id);
        }else{
            throw new RuntimeException("Please check the Driver ID.. No Such Driver..!");
        }
    }

    @Override
    public DriverDTO searchDriver(String id) {
        if (driverRepo.existsById(id)){
            return modelMapper.map(driverRepo.findById(id).get(), DriverDTO.class);
        }else{
            throw new RuntimeException("No Driver For "+id+" ..!");
        }
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> all = driverRepo.findAll();
        return modelMapper.map(all,new TypeToken<List<DriverDTO>>(){
        }.getType());
    }

    @Override
    public DriverDTO findName(String name) {
        Optional<Driver> driver = driverRepo.findByName(name);
        if (driver.isPresent()) {
            return modelMapper.map(driver.get(), DriverDTO.class);
        }
        return null;
    }

    @Override
    public DriverDTO findEmailAndPassword(String email, String password) {
        Optional<Driver> cus = driverRepo.findByEmailAndPassword(email,password);
        if (cus.isPresent()) {
            return modelMapper.map(cus.get(), DriverDTO.class);
        }
        throw new RuntimeException("Email name and Password Not Matched");

    }
}
