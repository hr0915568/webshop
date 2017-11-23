package models;

import helpers.RandomString;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;
import services.UserService;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
public class ForgottenPasswordCode extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;


    public Long user_id;

    @Constraints.Required
    public String code;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date validUntil = new Date();


    public static final Finder<Long, ForgottenPasswordCode> find = new Finder<>(ForgottenPasswordCode.class);

    private ForgottenPasswordCode(Long user_id) {
        this.user_id = user_id;
    }

    public static ForgottenPasswordCode generateNewCode(String email) {
        User user = UserService.findByEmail(email);

        ForgottenPasswordCode forgottenPasswordCode = new ForgottenPasswordCode(user.id);
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 24); // adds 24  hours
        forgottenPasswordCode.validUntil = cal.getTime();
        RandomString randomStringGenerator = new RandomString(25, ThreadLocalRandom.current());
        forgottenPasswordCode.code = randomStringGenerator.nextString();
        forgottenPasswordCode.save();

        return forgottenPasswordCode;
    }

    public static ForgottenPasswordCode findByCode(String code) {
        List<ForgottenPasswordCode> forgottenPasswordCodes = ForgottenPasswordCode.find.query().where().eq("code", code)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if(forgottenPasswordCodes.size() == 0 ) {
            return null;
        }

        return forgottenPasswordCodes.get(0);
    }
}
