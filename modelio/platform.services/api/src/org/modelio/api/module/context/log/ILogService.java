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

package org.modelio.api.module.context.log;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * The class implementing this interface provides a basic log service for
 * modules.
 */
@objid ("7c1f19c2-6bf5-11e0-a371-001ec947cd2a")
public interface ILogService {
    /**
     * Log the given exception with its stack trace as an error.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param t an exception to be displayed as a log.
     */
    @objid ("509889f3-a65c-11e1-833c-001ec947ccaf")
    void error(final Throwable t);

    /**
     * Output an error message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    @objid ("3adbc9d1-6bfd-11e0-a371-001ec947cd2a")
    void error(final String msg);

    /**
     * Log the given exception with its stack trace as an information.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param t an exception to be displayed as a log.
     */
    @objid ("509889e7-a65c-11e1-833c-001ec947ccaf")
    void info(final Throwable t);

    /**
     * Output an information message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    @objid ("8422bf70-557c-4925-8b84-158d2986aabe")
    void info(final String msg);

    /**
     * Log the given exception with its stack trace as a warning.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param t an exception to be displayed as a log.
     */
    @objid ("509889ed-a65c-11e1-833c-001ec947ccaf")
    void warning(final Throwable t);

    /**
     * Output a warning message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been
     * activated. The file and line of the log is displayed in the Modelio
     * console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    @objid ("3a433391-6bfd-11e0-a371-001ec947cd2a")
    void warning(final String msg);

}
