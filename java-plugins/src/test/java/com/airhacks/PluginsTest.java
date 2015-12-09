package com.airhacks;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PluginsTest {

    private ScriptEngine engine;

    @Before
    public void init() {
        ScriptEngineManager sem = new ScriptEngineManager();
        this.engine = sem.getEngineByName("javascript");
    }

    @Test
    public void execute() throws ScriptException {
        Runnable runnable = getPlugin();
        runnable.run();
    }

    public Runnable externalFunctionality() {
        return () -> {
            System.out.println("Hey duke");
        };
    }

    public Runnable getPlugin() throws ScriptException {
        this.engine.put("API", new ReportAPI());
        this.engine.eval("function run(){ print('hey nuke' + API.report);}");
        Invocable invocable = (Invocable) this.engine;
        return invocable.getInterface(Runnable.class);
    }

}
