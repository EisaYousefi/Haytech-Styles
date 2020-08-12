package com.haytech.haytechstyles;

public interface Validation {

    interface PassValidator {
        interface ValueText {
            void valueText(String text);

            void emptyText();
        }

        interface StatePass {
            void weak();

            void good();

            void strung();
        }
    }

    interface UsernameValidator {
        void userNameListener();
    }

    interface phoneValidator {

        void startPhoneNumber(String startPhoneNumber);

        void notValidationPhoneNumber(String lengthPhoneNumber);

        void emptyPhoneNumber();

        void validPhoneNumber();

    }
}
