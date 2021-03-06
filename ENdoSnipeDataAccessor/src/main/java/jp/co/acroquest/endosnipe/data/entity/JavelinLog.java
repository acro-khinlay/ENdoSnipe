/*******************************************************************************
 * ENdoSnipe 5.0 - (https://github.com/endosnipe)
 * 
 * The MIT License (MIT)
 * 
 * Copyright (c) 2012 Acroquest Technology Co.,Ltd.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package jp.co.acroquest.endosnipe.data.entity;

import java.io.InputStream;
import java.sql.Timestamp;

/**
 * Javelin ログテーブルに対するエンティティクラスです。<br />
 * 
 * @author y-komori
 */
public class JavelinLog
{
    /**
     * ログを一意に識別する ID 。<br/ >
     *
     * ID はシーケンス「SEQ_JAVELIN_LOG」によって発行します。
     */
    public long logId;

    /**
     * セッションを一意に識別するID。<br />
     *
     * ログファイルを複数レコードに分割して格納する場合、同じ SESSION_ID が割り当てられます。
     * ID はシーケンス「SEQ_JAVELIN_SESSION」によって発行します。
     */
    public long sessionId;

    /**
     * ログファイルを複数レコードに分割して格納する場合、順番を識別するための値。<br />
     *
     * 1 から始まる通番とします。<br />
     * 複数レコードに分割しない場合は 1 で固定とします。
     */
    public int sequenceId;

    /**
     * Javelin ログ本体。<br />
     */
    public InputStream javelinLog;

    /**
     * Javelin ログのファイル名。<br />
     */
    public String logFileName;

    /**
     * セッションの開始時刻。<br />
     */
    public Timestamp startTime;

    /**
     * セッションの終了時刻。<br />
     */
    public Timestamp endTime;

    /**
     * セッションに関する詳細説明。<br />
     *
     * 解析時にセッションへ説明を加えることを想定。
     */
    public String sessionDesc;

    /**
     * Javelinのログ種別を表す数値。<br />
     *
     * 1：CALL<br />
     * 2：RETURN<br />
     * 3：READ<br />
     * 4：WRITE<br />
     * 5：THROW<br />
     * 6：CATCH
     */
    public int logType;

    /**
     * CALL, RETURN ：呼び出されたメソッドの名前。<br />
     * READ, WRITE ：アクセスされたフィールドの名前。
     */
    public String calleeName;

    /**
     * CALL, RETURN ：呼び出されたメソッドのシグネチャ。<br />
     * READ, WRITE ：アクセスされたフィールドのシグネチャ。
     */
    public String calleeSignature;

    /**
     * Call, Return ：呼び出されたクラスの名前。<br />
     * Read, Write ：アクセスされたクラスの名前。<br />
     * Throw, Catch ：スローされた例外のクラス名。<br />
     * クラス名はパッケージも含めた名称とします。
     */
    public String calleeClass;

    /**
     * アクセス先フィールドの型。<br />
     *
     * クラス名はパッケージも含めた名称とします。
     */
    public String calleeFieldType;

    /**
     * Call, Return ：呼び出されたオブジェクトの識別子。<br />
     * Read, Write ：アクセスされたオブジェクトの識別子。<br />
     * Throw, Catch ： throw された例外のオブジェクトの識別子。
     */
    public int calleeObjectId;

    /**
     * Call, Return ：呼び出し元のメソッドの名前。<br />
     * Read, Write ：アクセス元メソッドの名前。<br />
     * Throw ：例外を throw したメソッドの名前。<br />
     * Catch ：例外を catch したメソッドの名前。
     */
    public String callerName;

    /**
     * Call, Return ：呼び出し元のメソッドのシグネチャ。<br />
     * Read, Write ：アクセス元メソッドのシグネチャ。<br />
     * Throw ：例外を throw したメソッドのシグネチャ。<br />
     * Catch ：例外を catch したメソッドのシグネチャ。
     */
    public String callerSignature;

    /**
     * Call, Return ：メソッドを呼び出したクラスの名前。<br />
     * Read, Write ：フィールドにアクセスしたクラスの名前。<br />
     * Throw ：例外をスローしたクラスの名前。<br />
     * Catch ：例外をキャッチしたクラスの名前。<br />
     * クラス名はパッケージも含めた名称とします。
     */
    public String callerClass;

    /**
     * Call, Return ：メソッドを呼び出したオブジェクトの識別子。<br />
     * Read, Write ：アクセス元オブジェクトの識別子。<br />
     * Throw ：例外をスローしたオブジェクトの識別子。<br />
     * Catch ：例外をキャッチしたオブジェクトの識別子。
     */
    public int callerObjectId;

    /**
     * イベントの警告レベル。以下の値とする。<br />
     * INFO：20、WARN:30、ERROR:40
     */
    public int eventLevel;

    /**
     * メソッドの実行にかかった時間。<br />
     */
    public long elapsedTime;

    /**
     * 呼び出し先メソッドのモディファイアを文字列で格納します。<br />
     */
    public String modifier;

    /**
     * ログ出力しているスレッドの名称。<br />
     */
    public String threadName;

    /**
     * ログ出力している Thread オブジェクトのパッケージ名を含めたクラス名。<br />
     */
    public String threadClass;

    /**
     * ログ出力しているスレッドのオブジェクト ID 。<br />
     */
    public int threadObjectId;

    /**
     * 計測項目の名前。<br />
     */
    public String measurementItemName;

    public long getLogId()
    {
        return logId;
    }

    public void setLogId(long logId)
    {
        this.logId = logId;
    }

    public long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(long sessionId)
    {
        this.sessionId = sessionId;
    }

    public int getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    public InputStream getJavelinLog()
    {
        return javelinLog;
    }

    public void setJavelinLog(InputStream javelinLog)
    {
        this.javelinLog = javelinLog;
    }

    public String getLogFileName()
    {
        return logFileName;
    }

    public void setLogFileName(String logFileName)
    {
        this.logFileName = logFileName;
    }

    public Timestamp getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Timestamp startTime)
    {
        this.startTime = startTime;
    }

    public Timestamp getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Timestamp endTime)
    {
        this.endTime = endTime;
    }

    public String getSessionDesc()
    {
        return sessionDesc;
    }

    public void setSessionDesc(String sessionDesc)
    {
        this.sessionDesc = sessionDesc;
    }

    public int getLogType()
    {
        return logType;
    }

    public void setLogType(int logType)
    {
        this.logType = logType;
    }

    public String getCalleeName()
    {
        return calleeName;
    }

    public void setCalleeName(String calleeName)
    {
        this.calleeName = calleeName;
    }

    public String getCalleeSignature()
    {
        return calleeSignature;
    }

    public void setCalleeSignature(String calleeSignature)
    {
        this.calleeSignature = calleeSignature;
    }

    public String getCalleeClass()
    {
        return calleeClass;
    }

    public void setCalleeClass(String calleeClass)
    {
        this.calleeClass = calleeClass;
    }

    public String getCalleeFieldType()
    {
        return calleeFieldType;
    }

    public void setCalleeFieldType(String calleeFieldType)
    {
        this.calleeFieldType = calleeFieldType;
    }

    public int getCalleeObjectId()
    {
        return calleeObjectId;
    }

    public void setCalleeObjectId(int calleeObjectId)
    {
        this.calleeObjectId = calleeObjectId;
    }

    public String getCallerName()
    {
        return callerName;
    }

    public void setCallerName(String callerName)
    {
        this.callerName = callerName;
    }

    public String getCallerSignature()
    {
        return callerSignature;
    }

    public void setCallerSignature(String callerSignature)
    {
        this.callerSignature = callerSignature;
    }

    public String getCallerClass()
    {
        return callerClass;
    }

    public void setCallerClass(String callerClass)
    {
        this.callerClass = callerClass;
    }

    public int getCallerObjectId()
    {
        return callerObjectId;
    }

    public void setCallerObjectId(int callerObjectId)
    {
        this.callerObjectId = callerObjectId;
    }

    public int getEventLevel()
    {
        return eventLevel;
    }

    public void setEventLevel(int eventLevel)
    {
        this.eventLevel = eventLevel;
    }

    public long getElapsedTime()
    {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }

    public String getModifier()
    {
        return modifier;
    }

    public void setModifier(String modifier)
    {
        this.modifier = modifier;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }

    public String getThreadClass()
    {
        return threadClass;
    }

    public void setThreadClass(String threadClass)
    {
        this.threadClass = threadClass;
    }

    public int getThreadObjectId()
    {
        return threadObjectId;
    }

    public void setThreadObjectId(int threadObjectId)
    {
        this.threadObjectId = threadObjectId;
    }

    public String getMeasurementItemName()
    {
        return measurementItemName;
    }

    public void setMeasurementItemName(String measurementItemName)
    {
        this.measurementItemName = measurementItemName;
    }

}
