package com.example.springblog.Services;

import com.example.springblog.models.Ad;
import com.example.springblog.models.Owner;
import com.example.springblog.repositories.AdRepository;
import com.example.springblog.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdService")
public class AdService {

    private final AdRepository adsDao;
    private final OwnerRepository ownerDao;
    private final EmailService emailService;

    public AdService(AdRepository adsDao, OwnerRepository ownerDao, EmailService emailService) {
        this.adsDao = adsDao;
        this.ownerDao = ownerDao;
        this.emailService = emailService;
    }

    public List<Ad> getAds(){
        List<Ad> ads = adsDao.findAll();
        for (Ad ad: ads){
            System.out.println("This ad title: " + ad.getTitle());
            System.out.println("This ad description: " + ad.getDescription());
            System.out.println("Owner: " + ad.getOwner().getFirstName() + " " + ad.getOwner().getLastName());
        }
        return ads;
    }

    public void createAd(Ad ad){
        Owner owner = ownerDao.getById(1L);
//        User user = userDao.getReferenceById(1L);
        ad.setOwner(owner);
        adsDao.save(ad);
        String subject = "Thank you for making an ad!";
        String description = "here is what you submitted" + ad.getDescription();
        emailService.prepareAndSend(ad, subject, description);
    }
}