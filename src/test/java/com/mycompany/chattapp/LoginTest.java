package com.mycompany.chattapp;
public class LoginTest {
    public static void main(String[] args) {
        System.out.println("========== LOGIN TESTS ==========\n");
        // TEST 1
        Login user1 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 1 - Valid Username: "
                + (user1.checkUserName() ? "PASSED" : "FAILED"));
        // TEST 2
        Login user2 = new Login(
                "kyle1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 2 - Invalid Username (no _): "
                + (!user2.checkUserName() ? "PASSED" : "FAILED"));
        // TEST 3
        Login user3 = new Login(
                "kyle_123",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 3 - Invalid Username (too long): "
                + (!user3.checkUserName() ? "PASSED" : "FAILED"));
        // TEST 4
        Login user4 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 4 - Valid Password: "
                + (user4.checkPasswordComplexity() ? "PASSED" : "FAILED"));
        // TEST 5
        Login user5 = new Login(
                "kyl_1",
                "Ch@1",
                "+27838968976"
        );
        System.out.println("TEST 5 - Invalid Password (too short): "
                + (!user5.checkPasswordComplexity() ? "PASSED" : "FAILED"));
        // TEST 6
        Login user6 = new Login(
                "kyl_1",
                "ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 6 - Invalid Password (no capital): "
                + (!user6.checkPasswordComplexity() ? "PASSED" : "FAILED"));
        // TEST 7
        Login user7 = new Login(
                "kyl_1",
                "Ch&&sec@ke!!",
                "+27838968976"
        );
        System.out.println("TEST 7 - Invalid Password (no number): "
                + (!user7.checkPasswordComplexity() ? "PASSED" : "FAILED"));
        // TEST 8
        Login user8 = new Login(
                "kyl_1",
                "Chandsecake99",
                "+27838968976"
        );
        System.out.println("TEST 8 - Invalid Password (no special): "
                + (!user8.checkPasswordComplexity() ? "PASSED" : "FAILED"));
        // TEST 9
        Login user9 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        System.out.println("TEST 9 - Valid Cell Number: "
                + (user9.checkCellPhoneNumber() ? "PASSED" : "FAILED"));
        // TEST 10
        Login user10 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "0838968976"
        );
        System.out.println("TEST 10 - Invalid Cell Number (no +27): "
                + (!user10.checkCellPhoneNumber() ? "PASSED" : "FAILED"));
        // TEST 11
        Login user11 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+2783896897"
        );
        System.out.println("TEST 11 - Invalid Cell Number (wrong length): "
                + (!user11.checkCellPhoneNumber() ? "PASSED" : "FAILED"));
        // TEST 12
        Login user12 = new Login(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
        String regMsg = user12.registerUser();
        System.out.println("TEST 12 - Successful Registration: "
                + (regMsg.equals("User has been registered successfully.")
                ? "PASSED" : "FAILED"));
        // TEST 13
        boolean loginOk = user12.loginUser(
                "kyl_1",
                "Ch&&sec@ke99!"
        );
        String successMsg = user12.returnLoginStatus(
                loginOk,
                "Kyle",
                "Smith"
        );
        System.out.println("TEST 13 - Successful Login + Message: "
                + (loginOk
                && successMsg.equals(
                        "Welcome Kyle, Smith it is great to see you again."
                )
                ? "PASSED" : "FAILED"));
        // TEST 14
        boolean loginFail = user12.loginUser(
                "wrong",
                "wrong"
        );
        String failMsg = user12.returnLoginStatus(
                loginFail,
                "Kyle",
                "Smith"
        );
        System.out.println("TEST 14 - Failed Login + Message: "
                + (!loginFail
                && failMsg.equals(
                        "Username or password incorrect, please try again."
                )
                ? "PASSED" : "FAILED"));
        System.out.println(
                "\n========== ALL 14 TESTS FINISHED =========="
        );
    }
}

