package droid.zaeem.notifierx.helpers;

/**
 * Created by Droid on 6/22/2016.
 */
public interface Constants {
    class URLs {
        public static final String BASE_URL = "http://zaeemsattar.com/";
        public static final String SIGNUP_VISITOR = BASE_URL + "notifierx/register_visitors.php";
        public static final String SIGNUP_STUDENT = BASE_URL + "notifierx/register_students.php";
        public static final String REFRESH_USER = BASE_URL + "notifierx/refresh_user.php";

    }

    class Keys {

        // data storage keys
        public static final String GCM_DEVICE_TOKEN = "gcm_device_token";
        public static final String GCM_DEVICE_TOKEN_NEW = "gcm_device_token_new";
        public static final String DEVICE_REGISTERED = "device_registered";
        public static final String TOKEN_SENT_TO_SERVER = "token_sent_to_server";
        public static final String INBOX_LIST = "inbox_list";
        public static final String INBOX_LIST_TITLE = "inbox_list_title";

        public static final String IMPORTANT_LIST = "important_list";
        public static final String IMPORTANT_LIST_TITLE = "important_list_title";

        public static final String STARRED_LIST = "starred_list";
        public static final String STARRED_LIST_TITLE = "starred_list_title";

        public static final String VISITOR_NAME = "visitor_name";
        public static final String VISITOR_EMAIL = "visitor_email";
        public static final String VISITOR_PHONE = "starred_phone";
        public static final String IS_STUDENT = "is_student";
        public static final String IS_IMPORTANT = "is_important";
        public static final String IS_INBOX = "is_inbox";
        public static final String STUDENT_NAME = "student_name";
        public static final String STUDENT_EMAIL = "student_email";
        public static final String STUDENT_PHONE = "student_phone";
        public static final String STUDENT_ROLLNUMBER = "student_rollnumber";
        public static final String STUDENT_CLASS = "student_class";
        public static final String STUDENT_YEAR = "student_year";

        public static final String MESSAGE = "message";
        public static final String MESSAGE_TITLE = "message_title";
        public static final String POSITION = "position";
        public static final String IMPORTANT = "important";
        public static final String TRUE = "true";
        public static final String FALSE = "false";
        public static final String STOP_NOTIFICATIONS = "stop_notifications";

        public static final String STUDENT = "student";
        public static final String VISITOR = "visitor";

        // post parameters

        public static final String ROLLNUMBER = "rollnumber";
        public static final String CLASS = "class";
        public static final String YEAR = "year";

        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String TOKEN = "token";

        public static final String USER = "user";
        public static final String OLD = "old";
        public static final String NEW = "new";

        //table columns

        public static final String TITLE ="title";
        public static final String BODY="body";
        // databse
        public static final String TABLE_INBOX = "inbox";
        public static final String TABLE_IMPORTANT = "important";
        public static final String TABLE_STARRED = "starred";


    }

}
