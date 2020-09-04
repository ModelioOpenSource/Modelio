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

package org.modelio.ui.swt;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;

/**
 * Helper service to run code in the SWT Display thread.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("de38fcad-0be4-450b-a85d-d20dc3383519")
public class SwtThreadHelper {
    /**
     * Creates a CompletableFuture that calls the callable in the display thread
     * that completes when callable has returned.
     * <p>
     * Exceptions throw by the callable make the future completes exceptionally.
     * <p>
     * Note that at the time the callable is invoked, widgets that have the receiver as their display may have been disposed.
     * Therefore, it is necessary to check for this case inside the callable before accessing the widget.
     * 
     * @param d the SWT Display
     * @param callable the code to call
     * @return a CompletableFuture that completes when callable has returned.
     * @since 3.7
     */
    @objid ("cbd64e76-d03e-4ad0-a313-fb077a3160b3")
    public static <T> CompletableFuture<T> asyncCall(Display d, Callable<T> callable) {
        CompletableFuture<T> ret = new CompletableFuture<>();
        d.asyncExec(() -> {
            try {
                T result = callable.call();
                ret.complete(result);
            } catch (Throwable e) {
                ret.completeExceptionally(e);
            }
        });
        return ret;
    }

    /**
     * Creates an {@link Executor} that runs runnables asynchronously in the {@link Display} thread.
     * <p>
     * May be used to chain {@link CompletableFuture} calls.
     * <p>
     * Note that at the time the runnable is invoked, widgets that have the receiver as their display may have been disposed.
     * Therefore, it is necessary to check for this case inside the runnable before accessing the widget.
     * 
     * @param d the SWT display
     * @return an Executor.
     * @since 3.7
     */
    @objid ("d891de70-3077-4ab5-9131-951b757d6ebf")
    public static Executor asyncExecutor(Display d) {
        return command -> d.asyncExec(command);
    }

    /**
     * Creates a {@link CompletableFuture} that calls the supplier in the display thread
     * and that completes when supplier has returned.
     * <p>
     * Exceptions throw by the supplier make the future completes exceptionally.
     * <p>
     * Note that at the time the supplier is invoked, widgets that have the receiver as their display may have been disposed.
     * Therefore, it is necessary to check for this case inside the supplier before accessing the widget.
     * 
     * @param d the SWT Display
     * @param q the code to call
     * @return a CompletableFuture that completes when callable has returned.
     * @since 3.7
     */
    @objid ("85f741b9-d913-4dec-b864-5f5f0411afbd")
    public static <T> CompletableFuture<T> asyncSupply(Display d, Supplier<T> q) {
        CompletableFuture<T> ret = new CompletableFuture<>();
        d.asyncExec(() -> {
            try {
                T result = q.get();
                ret.complete(result);
            } catch (Throwable e) {
                ret.completeExceptionally(e);
            }
        });
        return ret;
    }

    /**
     * Creates an {@link Executor} that runs runnables synchronously in the {@link Display} thread.
     * <p>
     * May be used to chain {@link CompletableFuture} calls.
     * {@link Executor#execute(Runnable)} may throw {@link SWTException} embedding exception thrown by the runnable.
     * <p>
     * Note that at the time the runnable is invoked, widgets that have the receiver as their display may have been disposed.
     * Therefore, it is necessary to check for this case inside the runnable before accessing the widget.
     * 
     * @param d the SWT display
     * @return an Executor.
     * @since 3.7
     */
    @objid ("cc79bcbb-53a0-46b3-bc9d-862953a5b7ab")
    public static Executor syncExecutor(Display d) {
        return command -> d.syncExec(command);
    }

    /**
     * Execute code that returns a value in the SWT Display thread synchronously.
     * <p>
     * Note that at the time the runnable is invoked, widgets that have the receiver as their display may have been disposed.
     * Therefore, it is necessary to check for this case inside the runnable before accessing the widget.
     * 
     * @param d the SWT Display.
     * @param q the code to execute
     * @return the value returned by the code.
     * @throws org.eclipse.swt.SWTException <ul>
     * <li>ERROR_FAILED_EXEC - if an exception occurred when executing the runnable</li>
     * <li>ERROR_DEVICE_DISPOSED - if the display has been disposed</li>
     * </ul>
     * @since 3.7
     */
    @objid ("7e88b1dc-602b-4f49-b597-7dbdce0290eb")
    public static <T> T syncSupply(Display d, Supplier<T> q) throws SWTException {
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) new Object[]{null};
        d.syncExec(() -> {
            ret [0] = q.get();
        });
        return ret[0];
    }

}
