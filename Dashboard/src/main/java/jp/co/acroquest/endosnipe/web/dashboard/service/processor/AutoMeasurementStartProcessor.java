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
package jp.co.acroquest.endosnipe.web.dashboard.service.processor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.acroquest.endosnipe.common.logger.ENdoSnipeLogger;
import jp.co.acroquest.endosnipe.web.dashboard.config.MeasurementSetting;
import jp.co.acroquest.endosnipe.web.dashboard.constants.EventConstants;
import jp.co.acroquest.endosnipe.web.dashboard.constants.LogMessageCodes;
import jp.co.acroquest.endosnipe.web.dashboard.manager.EventManager;
import jp.co.acroquest.endosnipe.web.dashboard.util.RequestUtil;

/**
 * 計測項目自動通知開始要求を処理するクラスです。
 * @author fujii
 *
 */
public class AutoMeasurementStartProcessor implements EventProcessor
{
    /** ロガー */
    private static final ENdoSnipeLogger LOGGER =
            ENdoSnipeLogger.getLogger(AutoMeasurementStartProcessor.class);

    /**
     * コンストラクタ
     */
    public AutoMeasurementStartProcessor()
    {

    }

    /**
     * 計測項目自動通知開始要求を処理します。
     * @param request {@link HttpServletRequest}オブジェクト
     * @param response {@link HttpServletResponse}オブジェクト
     */
    public void process(final HttpServletRequest request, final HttpServletResponse response)
    {
        String agentIds = request.getParameter(EventConstants.AGENT_IDS);
        String graphIdStr = request.getParameter(EventConstants.GRAPH_ID);
        String measurementTypes = request.getParameter(EventConstants.MEASUREMENT_TYPES);
        String clientId = request.getParameter(EventConstants.CLIENT_ID);
        String itemName = request.getParameter(EventConstants.ITEM_NAME);

        if (graphIdStr == null)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_GRAPH_ID);
            return;
        }
        if (agentIds == null)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_AGENT_ID);
            return;
        }
        if (measurementTypes == null)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_MEASUREMENT_TYPE);
            return;
        }
        if (clientId == null)
        {
            LOGGER.log(LogMessageCodes.NO_CLIENT_ID);
            return;
        }

        Integer graphId = null;
        try
        {
            graphId = Integer.valueOf(graphIdStr);
        }
        catch (NumberFormatException ex)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_GRAPH_ID, graphId);
            return;
        }

        List<Integer> agentIdList = RequestUtil.getAgentIdList(agentIds);
        if (agentIdList == null || agentIdList.size() == 0)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_AGENT_ID, agentIds);
            return;
        }

        List<Integer> measurementTypeList = RequestUtil.getMeasurementTypeList(measurementTypes);
        if (measurementTypeList == null || measurementTypeList.size() == 0)
        {
            LOGGER.log(LogMessageCodes.UNKNOWN_MEASUREMENT_TYPE, measurementTypes);
            return;
        }

        EventManager manager = EventManager.getInstance();
        // 計測項目を追加します。
        for (Integer agentId : agentIdList)
        {
            MeasurementSetting setting = manager.getMeasurementSettings(clientId);
            if (setting == null)
            {
                setting = new MeasurementSetting();
                manager.addMeasurementSetting(clientId, setting);
            }
            setting.addMeasurementType(graphId, agentId, measurementTypeList, itemName);
            setting.setAutoNotify(graphId, true);
        }

    }

}
