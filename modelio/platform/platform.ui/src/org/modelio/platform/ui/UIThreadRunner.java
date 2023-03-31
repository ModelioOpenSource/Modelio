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
package org.modelio.platform.ui;

import java.util.concurrent.Executor;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.modelio.platform.ui.plugin.UI;

/**
 * Utility class to execute Runnable in the UIThread and in the context of a non-disposed widget.
 * <p>
 * The {@link #asynExec(Widget, Runnable)} and {@link #syncExec(Widget, Runnable)} methods expect a widget as their first parameter.<br/>
 * The widget is used as a context to be checked for non-disposed state before executing the runnable.<br/>
 * In typical cases the context widget will be the widget modified by the runnable or any suitable parent widget.
 * Right before the runnable is executed, the context widget is checked. If the context widget is disposed
 * the runnable code will be dropped.
 * <p>
 * When trace level is DEBUG, a message will be dropped in the log when
 * the "disposed context widget" situations occurs.
 * 
 * @since Modelio 5.0
 */
@objid ("4fae419d-e35b-4131-9fef-57352efb13ed")
public class UIThreadRunner {
    /**
     * UIThread safe {@link Display#asyncExec(Runnable)} asynExec()
     * @param context a valid widget to be checked at execution time of the runnable
     * @param runnable the runnable to execute
     * @throws IllegalArgumentException if context is <code>null</code> or already disposed.
     */
    @objid ("55699bbb-37e8-4984-99c0-2b0368dcbe42")
    public static void asynExec(Widget context, Runnable runnable) throws IllegalArgumentException {
        if (context == null || context.isDisposed()) {
            throw new IllegalArgumentException("'" + context + "' context widget is disposed, runnable ignored");
        }
        context.getDisplay().asyncExec(new SafeRunnable(context, runnable));
        
    }

    /**
     * UIThread safe synExec()
     * @param context a valid widget to be checked at execution time of the runnable
     * @param runnable the runnable to execute
     * @throws IllegalArgumentException if context is <code>null</code> or already disposed.
     */
    @objid ("89c02d4b-1b19-45c2-8b87-aa053200e22c")
    public static void syncExec(Widget context, Runnable runnable) throws IllegalArgumentException {
        if (context == null || context.isDisposed()) {
            throw new IllegalArgumentException("'" + context + "' context widget is disposed, runnable ignored");
        }
        context.getDisplay().syncExec(new SafeRunnable(context, runnable));
        
    }

    /**
     * UIThread safe {@link Display#timerExec(int, Runnable)}
     * <p>
     * Also work around {@link Display#timerExec(int, Runnable)} must be called from the UI thread.
     * @param context a valid widget to be checked at execution time of the runnable
     * @param milliseconds the delay before running the runnable
     * @param runnable the runnable to execute
     * @throws IllegalArgumentException if context is <code>null</code> or already disposed.
     * @since Alouette 5.3
     */
    @objid ("1d3f7e09-ae80-482c-ba23-e7e4db6b6dc0")
    public static void timerExec(Widget context, int milliseconds, Runnable runnable) throws IllegalArgumentException {
        if (context == null || context.isDisposed()) {
            throw new IllegalArgumentException("'" + context + "' context widget is disposed, runnable ignored");
        }
        
        if (Display.getCurrent() ==  context.getDisplay()) {
            // Right thread, schedule immediately
            context.getDisplay().timerExec(milliseconds, new SafeRunnable(context, runnable));
        } else {
            // Schedule the timer asynchronously
            asynExec(context, () ->
            context.getDisplay().timerExec(milliseconds, new SafeRunnable(context, runnable)));
        }
        
    }

    /**
     * Create an {@link Executor} that executes submitted {@link Runnable runnables} by calling {@link #syncExec(Widget, Runnable)}.
     * <p>
     * To be used for {@link java.util.concurrent.CompletableFuture#runAsync(Runnable, Executor)} by example.
     * @see java.util.concurrent.CompletableFuture CompletableFuture methods that use the returned executor.
     * @since Alouette 5.3
     * @param context a valid widget to be checked at execution time of the runnable
     * @return an executor.
     */
    @objid ("4e32223f-bdaa-4e67-9afe-fa04a7087e8c")
    public static Executor syncExecutor(Widget context) {
        return runnable -> syncExec(context, runnable);
    }

    /**
     * Create an {@link Executor} that executes submitted {@link Runnable runnables} by calling {@link #syncExec(Widget, Runnable)}.
     * <p>
     * To be used for {@link java.util.concurrent.CompletableFuture#runAsync(Runnable, Executor)} by example.
     * @see java.util.concurrent.CompletableFuture CompletableFuture methods that use the returned executor.
     * @since Alouette 5.3
     * @param context a valid widget to be checked at execution time of the runnable
     * @return an executor.
     */
    @objid ("f86a8691-1e53-43a9-a053-024a83ed9585")
    public static Executor asyncExecutor(Widget context) {
        return runnable -> syncExec(context, runnable);
    }

    @objid ("4727b5dd-c0e3-4b55-a3bc-019b730031ab")
    private static class SafeRunnable implements Runnable {
        @objid ("1536f2ef-172c-4b63-9c8a-cc52c60cfddb")
        private final Widget widget;

        @objid ("b5abfbe9-e830-4792-9ead-ead7aecb0433")
        private final Runnable runnable;

        @objid ("f6674228-9bc2-4308-8cea-3a6c53b8a2a7")
         SafeRunnable(Widget contextWidget, Runnable runnable) {
            this.widget = contextWidget;
            this.runnable = runnable;
            
        }

        @objid ("320221e8-7878-42ec-b15e-44e5a8f11e1f")
        @Override
        public void run() {
            if (!this.widget.isDisposed()) {
                try {
                    this.runnable.run();
                } catch (SWTException e) {
                    if (e.code == SWT.ERROR_DEVICE_DISPOSED) {
                        UI.LOG.debug("UIThreadRunner: '%s' context widget is disposed, runnable ignored", this.widget);
                    } else {
                        UI.LOG.debug("UIThreadRunner: runnable failed.");
                        UI.LOG.error(e);
                    }
                }
            } else {
                UI.LOG.debug("UIThreadRunner: '%s' context widget is disposed, runnable ignored", this.widget);
            }
            
        }

    }

}
