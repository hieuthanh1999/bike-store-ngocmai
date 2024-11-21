package com.service;

import java.util.List;
import java.util.Optional;

import com.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.ColorRepository;
@Service
public class ColorService {
 @Autowired
 ColorRepository srepo;
 public List<Color> listAll(){
	 return srepo.findAll();
 }
 public List<Color> listByProductId(int id){
	 return srepo.findColorByProductId(id);
 }
 public Optional<Color> listByName(int pid, String name){
	 return srepo.findColorByName(pid, name);
 }
 public List<Color> listByProductAndAmount(int pid){
	 return srepo.findColorByProductAndAmount(pid);
 }
 public void add(Color s) {
	 srepo.save(s);
 }
 public void delete(int id) {
	 srepo.deleteById(id);
 }
 public Color get(int id) {
	 return srepo.findById(id).get();
 }
}
