package model;

/**
 * Created by Administrator on 2017-02-27.
 */

    public class UserVO {
        private int UserId;
        private String UserName;
        private String Password;

        public UserVO() {

        }

        public UserVO(String UserName, String Password) {
            super();
            this.UserName = UserName;
            this.Password = Password;
        }

        public UserVO(int UserId, String UserName, String Password) {
            super();
            this.UserId = UserId;
            this.UserName = UserName;
            this.Password = Password;
        }
        public int getUserId() {
            return UserId;
        }
        public void setUserId(int UserId) {
            this.UserId = UserId;
        }
        public String getUserName() {
            return UserName;
        }
        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
        public String getPassword() {
            return Password;
        }
        public void setPassword(String Password) {
            this.Password = Password;
        }
    }

