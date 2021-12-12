package com.binh.security.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    private String fullname;
    
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String photo;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String password;
  
//    public String getUsername() {
//        return username;
//    }
// 
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    
//    public String getFullname() {
//		return fullname;
//	}
//
//	public void setFullname(String fullname) {
//		this.fullname = fullname;
//	}
//
//	public String getEmail() {
//        return email;
//    }
// 
//    public void setEmail(String email) {
//        this.email = email;
//    }
// 
//    public String getPassword() {
//        return password;
//    }
// 
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    
//    public String getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(String photo) {
//		this.photo = photo;
//	}
//
//	public Set<String> getRole() {
//      return this.role;
//    }
//    
//    public void setRole(Set<String> role) {
//      this.role = role;
//    }
}
