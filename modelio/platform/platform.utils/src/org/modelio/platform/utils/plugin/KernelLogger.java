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
package org.modelio.platform.utils.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.IBasicLogger;
import org.slf4j.LoggerFactory;

/**
 * Adapter from {@link IBasicLogger} to slf4j {@link org.slf4j.Logger}.
 * <p>
 * Redirect modelio kernel logs to the slf4j logger plugged to the Eclipse log in the {@link Log} class.
 * This logger skips the Eclipse log framework.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("7c68530b-af85-43e2-b42f-b380fd2f19bc")
class KernelLogger implements IBasicLogger {
    @objid ("f4dd6e1c-f3e5-438e-83bd-050be85cdd2a")
    private int logLevel = IBasicLogger.TRACE;

    @objid ("28256d5a-4a14-4f42-bcba-15f25e3e4298")
    private org.slf4j.Logger service;

    @objid ("b0765e71-93f3-4c5b-ad42-79d49e3280e1")
    public  KernelLogger() {
        this.service = LoggerFactory.getLogger("modelio.core");
    }

    @objid ("34b3f253-6d47-4ffa-aebc-fd98e20f7b32")
    @Override
    public void error(String message) {
        if (isErrorEnabled()) {
            this.service.error(message);
        }
        
    }

    @objid ("a3dee50f-675e-4d62-9e5c-1f39684640d0")
    @Override
    public void error(String format, Object... args) {
        if (isErrorEnabled()) {
            this.service.error(String.format(format, args));
        }
        
    }

    @objid ("62bcd9a6-517c-4227-ad74-c3c75885cb81")
    @Override
    public void error(Throwable ex) {
        if (isErrorEnabled()) {
            this.service.error(ex.getMessage(), ex);
        }
        
    }

    @objid ("6ab392b3-433d-42f9-bb35-2692c3f7eeb5")
    @Override
    public void trace(String message) {
        if (isTraceEnabled()) {
            this.service.trace(message);
        }
        
    }

    @objid ("55b79b0c-008f-4660-a1e2-aaecb00335cd")
    @Override
    public void trace(String format, Object... args) {
        if (isTraceEnabled()) {
            this.service.trace(String.format(format, args));
        }
        
    }

    @objid ("904a051b-cfdc-49b4-9f43-7b48b03e6abd")
    @Override
    public void trace(Throwable ex) {
        if (isTraceEnabled()) {
            this.service.trace(ex.getMessage(), ex);
        }
        
    }

    @objid ("ed41c336-512f-4948-8587-f854f7a51753")
    @Override
    public void warning(String message) {
        if (isWarnEnabled()) {
            this.service.warn(message);
        }
        
    }

    @objid ("f0cffd82-1857-4ddb-8b9a-c78b2a783b4c")
    @Override
    public void warning(String format, Object... args) {
        if (isWarnEnabled()) {
            this.service.warn(String.format(format, args));
        }
        
    }

    @objid ("028ddeb7-913f-4298-a969-00068a42a533")
    @Override
    public void warning(Throwable ex) {
        if (isWarnEnabled()) {
            this.service.warn(ex.getMessage(), ex);
        }
        
    }

    @objid ("bad7a409-606f-43a2-aa7d-93af0d2c1ca3")
    @Override
    public int getLevel() {
        return this.logLevel;
    }

    @objid ("fc97aa9e-781c-4c03-9bd6-40085942b800")
    @Override
    public void setLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    @objid ("e4a7f040-2a76-4e8e-9f16-6cd90b28148c")
    private boolean isTraceEnabled() {
        return this.logLevel <= IBasicLogger.TRACE && this.service.isTraceEnabled();
    }

    @objid ("e8a1fe42-e5bf-4c6d-b66a-cb8ce53bb9e6")
    private boolean isWarnEnabled() {
        return this.logLevel <= IBasicLogger.WARNING && this.service.isWarnEnabled();
    }

    @objid ("168bbf1e-7edd-4fa2-84e5-8a1faa8a6dd3")
    private boolean isErrorEnabled() {
        return this.logLevel <= IBasicLogger.ERROR && this.service.isErrorEnabled();
    }

}
