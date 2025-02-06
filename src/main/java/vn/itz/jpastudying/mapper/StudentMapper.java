package vn.itz.jpastudying.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.itz.jpastudying.Dto.request.StudentCreateRequestDto;
import vn.itz.jpastudying.Dto.request.StudentUpdateRequestDto;
import vn.itz.jpastudying.Dto.response.StudentResponseDto;
import vn.itz.jpastudying.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  @Mapping(source = "userName", target = "username")
  @Mapping(source = "fullName", target = "fullname")
  @Mapping(source = "birthDate", target = "birthday")
  @Mapping(source = "passWord", target = "password")
  Student convertToStudent(StudentCreateRequestDto requestDto);

  @Mapping(source = "id", target = "studentId")
  @Mapping(source = "username", target = "userName")
  @Mapping(source = "fullname", target = "fullName")
  @Mapping(source = "birthday", target = "birthDate")
  @Mapping(source = "password", target = "passWord")
  StudentResponseDto convertToStudentResponse(Student student);

  @IterableMapping(elementTargetType = StudentResponseDto.class)
  List<StudentResponseDto> convertToListStudentResponse(List<Student> students);

  @Mapping(source = "birthDate", target = "birthday")
  @Mapping(source = "userName", target = "username")
  @Mapping(source = "fullName", target = "fullname")
  void updateStudent(@MappingTarget Student student, StudentUpdateRequestDto studentDto);
}
