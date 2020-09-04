/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.modelermodule.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Message service
 * <br>Use of "i18n/messages"
 */
@objid ("584d952e-fcbf-4714-bac3-39bdc788eda8")
public class I18nMessageService {
    @objid ("d2690564-7265-49eb-9425-1f5167a880ed")
    private static final String FILE_NAME_MESSAGES = "org/modelio/module/modelermodule/i18n/messages";

    @objid ("95ad12cc-953b-49e0-b0ac-6142878e8657")
    private static I18nMessageService instance;

    @objid ("3ea4a7ed-0d81-4532-af69-b30d0414910d")
    private ResourceBundle messageResource;

    /**
     * Constructor.
     */
    @objid ("07afc650-a6d6-49a3-b4ac-177cfb5b7ae4")
    private I18nMessageService() {
        Locale locale = Locale.getDefault();
        this.messageResource = ResourceBundle.getBundle(FILE_NAME_MESSAGES, locale);
    }

    /**
     * Singleton creation.
     */
    @objid ("4a6694d5-0f79-4ddb-b482-452a75e15992")
    private static I18nMessageService getInstance() {
        if (null == instance) { // Init on first call
            instance = new I18nMessageService();
        }
        return instance;
    }

    /**
     * @return the messageResource
     */
    @objid ("b610120d-efba-47d4-bd6f-1a420fb9867f")
    private ResourceBundle getMessageResource() {
        return this.messageResource;
    }

    /**
     * Get message value from key.
     * 
     * @param key the key for the desired string.
     * @return the string for the given key.
     */
    @objid ("0ebe3d46-8480-4071-8882-2c4e37d98a12")
    public static String getString(final String key) {
        return getInstance().getMessageResource().getString(key);
    }

    /**
     * Get list of messages values from key with parameters.
     * 
     * @param key the key for the desired string.
     * @param params an array of objects to be formatted and substituted.
     * @return the string for the given key.
     */
    @objid ("fa06c29f-9349-4504-bb37-46bb630f4815")
    public static String getString(final String key, final String... params) {
        String message = null;
        try {
            String value = getString(key);
            message = MessageFormat.format(value, (Object[]) params);
        } catch (MissingResourceException e) {
            message = '!' + key + '!';
        }
        return message;
    }

}
