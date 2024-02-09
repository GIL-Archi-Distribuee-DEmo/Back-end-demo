package com.gil.school.api.controller.grpc;

import com.gil.school.api.model.Course;
import com.gil.school.api.services.CourseService;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GrpcService
public class CourseReactiveService extends ReactorCourseServiceGrpc {





}
