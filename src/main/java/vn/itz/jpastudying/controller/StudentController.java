package vn.itz.jpastudying.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;
import vn.itz.jpastudying.service.StudentDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
public class StudentController {
  @Autowired
  private StudentDaoService studentDaoService;

  // Lay tat ca du lieu trong bang sinh vien
  @GetMapping("/students")
  public ResponseEntity<ApiMessageDto<List<Student>>> getAllStudents(){
    ApiMessageDto<List<Student>> respone = ApiResponeUtils.results("Danh sach sinh vien",
        studentDaoService.findAllStudents());
    return ResponseEntity.ok(respone);
  }

//   Lay ra du lieu cua mot sinh vien
  @GetMapping("/student/{id}")
  public ResponseEntity<ApiMessageDto<Student>> getStudentById(@PathVariable int id) {

    ApiMessageDto<Student> response = ApiResponeUtils.results("Thong tin sinh vien",
        studentDaoService.findStudentById(id));
    return ResponseEntity.ok(response);
  }

  // Them mot sinh vien vao bang
  @PostMapping("/student")
  public ResponseEntity<ApiMessageDto<Student>> createStudent(@Valid @RequestBody Student student) {
    ApiMessageDto<Student> response = ApiResponeUtils.results("Them du lieu sinh vien thanh cong",
        studentDaoService.createStudent(student));
    return ResponseEntity.ok(response);
  }

  // Xoa mot sinh vien ra khoi bang
  @DeleteMapping("/student/{id}")
  public ResponseEntity<ApiMessageDto<Void>> deleteStudent(@PathVariable int id) {
    studentDaoService.deleteStudent(id);
    ApiMessageDto<Void> respone = ApiResponeUtils.results("Xoa du lieu sinh vien thanh cong",
        null);
    return ResponseEntity.ok(respone);
  }

  // Cap nhat du lieu sinh vien trong bang
  @PutMapping("/student/{id}")
  public ResponseEntity<ApiMessageDto<Student>> updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
    ApiMessageDto<Student> response = ApiResponeUtils.results("Cap nhat du lieu sinh vien thanh cong",
        studentDaoService.updateStudent(id, student));
    return ResponseEntity.ok(response);
  }

  // Lay tat ca danh sach khoa hoc ma mot sinh vien dang ky
  @GetMapping("/student/{student_id}/subjects")
  public ResponseEntity<ApiMessageDto<List<Subject>>> getEnrolledSubjects(@PathVariable int student_id){
    ApiMessageDto<List<Subject>> response = ApiResponeUtils.results("Danh sach khoa hoc ma sinh vien dang ky",
        studentDaoService.getEnrolledSubjects(student_id));
    return ResponseEntity.ok(response);
  }

  // Dang ky khoa hoc cho sinh vien
  @PostMapping("/student/{student_id}/subject/{subject_id}")
  public ResponseEntity<ApiMessageDto<Student>> enrollSubject(@PathVariable int student_id, @PathVariable int subject_id){
    ApiMessageDto<Student> respone = ApiResponeUtils.results("Dang ky khoa hoc thanh cong",
        studentDaoService.enrollSubject(student_id, subject_id));
    return ResponseEntity.ok(respone);
  }

  // Xoa mot khoa hoc da dang ky
  @DeleteMapping("/student/{student_id}/subject/{subject_id}")
  public ResponseEntity<ApiMessageDto<Student>> removeSubject(@PathVariable int student_id, @PathVariable int subject_id){
    ApiMessageDto<Student> respone = ApiResponeUtils.results("Xoa khoa hoc thanh cong",
        studentDaoService.removeSubject(student_id, subject_id));
    return ResponseEntity.ok(respone);
  }
}
