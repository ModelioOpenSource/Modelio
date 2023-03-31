/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.gproject;

import java.io.IOException;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.vbasic.files.FileUtils;

/**
 * Represents a problem in the project .
 * <p>
 * A problem may occur when opening a project part, when synchronizing the project from its remote location, ...
 */
@objid ("9f29b0fe-52a0-461a-b02c-7721677082f4")
public class GProblem {
    @objid ("19867e38-42e9-4c47-baa8-61047fc705f2")
    private final String subject;

    @objid ("716a2fd8-eb5f-4306-8f2b-cccd88d0c933")
    private final Throwable cause;

    @objid ("958f2d60-f296-4a48-8317-ebcd081dcc75")
    @Override
    public String toString() {
        return "GProblem [subject=" + this.subject + ", cause=" + this.cause + "]";
    }

    /**
     * @return a user friendly label of the problem location.
     */
    @objid ("1a050ccc-6036-4fef-939e-ca8f1d31cbd5")
    public String getSubject() {
        return this.subject;
    }

    /**
     * @return a user friendly label of the problem
     */
    @objid ("f9235e88-c287-4ab3-8fab-0c9805cad24c")
    public String getProblem() {
        if (this.cause instanceof IOException) {
            return FileUtils.getLocalizedMessage((IOException) this.cause);
        } else if (this.cause instanceof org.modelio.vcore.smkernel.AccessDeniedException) {
            return this.cause.getLocalizedMessage();
        } else if (this.cause instanceof RuntimeException) {
            return this.cause.toString();
        } else {
            return this.cause.getLocalizedMessage();
        }
        
    }

    /**
     * @return the exception that occurred.
     */
    @objid ("b4b01d7f-734e-4d24-9ba5-d73937261bce")
    public Throwable getCause() {
        return this.cause;
    }

    /**
     * @param subject a user friendly label for the project part.
     * @param cause the cause of the failure
     */
    @objid ("0f2e8351-b9d9-485a-8053-48de8b805aa6")
    public  GProblem(String subject, Throwable cause) {
        this.subject = subject;
        this.cause = cause;
        
    }

    /**
     * Create a problem on a project part.
     * @param part a project part
     * @param cause the cause of the failure
     */
    @objid ("35ae9226-a768-46ad-8a54-820e3b0425a7")
    public  GProblem(IGPart part, Throwable cause) {
        this(String.format("[%s: %s v%s]", part.getType().name(), part.getId(), Objects.toString(part.getVersion())), cause);
    }

    /**
     * Create a problem on a part that couldn't be added.
     * @param d a project part descriptor
     * @param cause the cause of the failure
     */
    @objid ("e25d5c53-0bb5-47c8-b61e-0865b2943b06")
    public  GProblem(GProjectPartDescriptor d, Throwable cause) {
        this(String.format("[%s: %s v%s]", d.getType().name(), d.getId(), Objects.toString(d.getVersion())), cause);
    }

    /**
     * Create a problem on the project itself.
     * @param d a project descriptor
     * @param cause the cause of the failure
     */
    @objid ("4e0b50a8-b6a1-4825-a763-4ec75a7f6034")
    public  GProblem(GProjectDescriptor d, Throwable cause) {
        this(String.format("[Project: %s %s]", d.getName(), Objects.toString(d.getRemoteLocation())), cause);
    }

}
