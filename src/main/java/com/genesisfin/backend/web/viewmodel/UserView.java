package com.genesisfin.backend.web.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView {
   private Long id;
   private String name;
   private String password;
   private String email;
}
