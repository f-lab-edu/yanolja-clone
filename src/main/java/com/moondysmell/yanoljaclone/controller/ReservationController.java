package com.moondysmell.yanoljaclone.controller;

import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.dto.ReservationRequestDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepository;
import com.moondysmell.yanoljaclone.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

   // @Autowired
  //  AccommodationService accomRepository;

  //  @Autowired
  //  UserRepository userRepository;

    @Autowired
    private final ReservationService reservService;

    //@Autowired
   // private final ReservationRepository reservRepository;


   /* @PostMapping("/make")
    public ResponseEntity<Reservation> makeReservarion(@RequestBody Reservation reserv, @ModelAttribute ReservationRequestDto reservRequestDto, RedirectAttributes redirectAttributes) {
       try {
            //Reservation reserv = reservRepository.save(new Reservation(reserv.getUser().getName(), reserv.getUser().getPhone_num(),reserv.getTrans_type(), reserv.getCheckin(), reserv.getCheckout(), reserv.getPayment_type(), reserv.getAccom().getPrice(), );
            //Reservation reserv = reservRepository.save(new Reservation( ));
            //return new ResponseEntity<>();
           int makeReservationId = reservService.r
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Reservation reserv = reservRepository.findByAccomAndUser()
    }*/




    @GetMapping("/reserv")
    public String findByDetail(@RequestParam ReservationRequestDto reservedRequestDto){
        String user_name = reservedRequestDto.getName();
        int reserv_id = reservedRequestDto.getReserv_id();
        String phone_num = reservedRequestDto.getPhone_num();

        //List<ReservationResponseDto> reservedResult = reservService.getReservedResult(user_name, reserv_id, phone_num);
        List<ReservationResponseDto> reservedResult = reservService.getReservedResult(user_name, reserv_id, phone_num);
        if(reservedResult.isEmpty()){
            return "";
        }
       // model.addAttribute("reservedResult", reservedResult);
            return "redirect:/reservation";
       // return reservedResult.stream()
         //       .map(reserv -> new ReservationResponseDto(reserv))
          //      .collect(Collectors.toList());
        //return reservService.findByDetail();
        /*List<Reservation> reserv = reservRepository.findByAccomAndUser(reserv_id, name, phone_num);
               // .orElseThrow()
        if(reserv.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reserv, HttpStatus.OK);
     */
    }


}
