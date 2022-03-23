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
package org.modelio.vcore.session.api.transactions;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.plugin.VCoreSession;

/**
 * Indicates that another transaction is already open in another thread.
 * <p>
 * This exception contains many details :
 * <ul>
 * <li> The name of the transaction that couldn't be created,
 * <li> The blocking concurrent transaction,
 * <li> The blocking concurrent thread,
 * <li> The time waited before this exception was thrown,
 * <li> As a suppressed exception, the stack trace of the blocking thread,
 * <li> As a suppressed exception, the stack trace of the blocking transaction creation.
 * </ul>
 */
@objid ("a592a495-19f4-4cdb-80eb-018080e4d826")
public class ConcurrentTransactionException extends TransactionCreationException {
    @objid ("33edbead-6831-4970-b412-6302a339b14a")
    private static final long serialVersionUID = 1L;

    @objid ("eb22afa7-8a78-4e59-b44f-a62538182efb")
    private String failedName;

    @objid ("f7725a07-d3fb-4182-bcc6-76414dacff15")
    private long waitedTime;

    @objid ("525467b9-5c6d-4d31-9670-d5217d1aa4e8")
    private TimeUnit waitedTimeUnit;

    @objid ("1248d31c-af3e-4b01-9552-156cd312284a")
    private transient ITransaction runningTransaction;

    @objid ("1cffc580-58b4-4641-abeb-6fe572aa67a9")
    private transient Thread concurrentThread;

    /**
     * Initialize the exception.
     * @param trName the failed transaction name
     * @param runningTransaction the still running transaction
     * @param otherTread the thread already running a transaction
     * @param concurrentTransactionCreation stack trace of the running transaction creation
     * @param timeout the time waited.
     * @param unit the waited time unit.
     */
    @objid ("5d681c4f-a473-42f3-8f2d-35c0ae4b36bf")
    public  ConcurrentTransactionException(String trName, ITransaction runningTransaction, Thread otherTread, Throwable concurrentTransactionCreation, long timeout, TimeUnit unit) {
        super(null);
        
        this.failedName = trName;
        this.runningTransaction = runningTransaction;
        this.waitedTime = timeout;
        this.waitedTimeUnit = unit;
        this.concurrentThread = otherTread;
        StackTraceElement[] runningStack = this.concurrentThread.getStackTrace();
        
        String msg = VCoreSession.I18N.getMessage("ConcurrentTransactionException.st", 
                runningTransaction.getName(), 
                otherTread.getName());
        Throwable t = new Throwable(msg);
        t.setStackTrace(runningStack);
        
        addSuppressed(t);
        addSuppressed(concurrentTransactionCreation);
        
    }

    @objid ("fabc1e53-9c6e-4db2-95a6-c110aefb3211")
    @Override
    public String getMessage() {
        String msg = VCoreSession.I18N.getMessage("ConcurrentTransactionException", 
                this.failedName, 
                this.waitedTime, 
                this.waitedTimeUnit, this.concurrentThread, this.runningTransaction.getName() );
        /*String msg = "Failed creating "+this.failedName+" transaction after having waited "
                + this.waitedTime + " " + this.waitedTimeUnit + "."
                + "The " + this.concurrentThread+" thread is still running the "
                + this.runningTransaction.getName() +" transaction";*/
        return msg;
    }

    /**
     * Get the name of the transaction that couldn't be created.
     * @return the unborn transaction name.
     */
    @objid ("779f7b75-c194-4143-a601-72cab3ae8731")
    public String getFailedName() {
        return this.failedName;
    }

    /**
     * Get the running transaction that prevents this thread from creating a transaction.
     * @return the concurrent thread.
     */
    @objid ("5e968d38-1c5a-4406-b06e-8e0e84b1d1b8")
    public ITransaction getRunningTransaction() {
        return this.runningTransaction;
    }

    /**
     * @return the time waited before throwing this exception.
     */
    @objid ("a35f0cad-f147-4b7b-bb41-bd327886e73b")
    public long getWaitedTime() {
        return this.waitedTime;
    }

    /**
     * @return the time unit of {@link #getWaitedTime()}.
     */
    @objid ("3368c9b0-2fd4-4729-bbb1-1b92dac92670")
    public TimeUnit getUnit() {
        return this.waitedTimeUnit;
    }

    /**
     * Get the concurrent transaction running the blocking {@link #getRunningTransaction() transaction}.
     * @return the concurrent transaction.
     */
    @objid ("a8a50c15-f67d-4671-8eff-ae8571b6180f")
    public Thread getConcurrentThread() {
        return this.concurrentThread;
    }

}
