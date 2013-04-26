/**
 * Copyright (C) 2012, Grass CRM Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcrm.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gcrm.domain.Account;
import com.gcrm.domain.Campaign;
import com.gcrm.domain.Contact;
import com.gcrm.domain.Currency;
import com.gcrm.domain.Document;
import com.gcrm.domain.LeadSource;
import com.gcrm.domain.Opportunity;
import com.gcrm.domain.OpportunityType;
import com.gcrm.domain.SalesStage;
import com.gcrm.domain.User;
import com.gcrm.service.IBaseService;
import com.gcrm.service.IOptionService;
import com.gcrm.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * Edits Opportunity
 * 
 */
public class EditOpportunityAction extends BaseEditAction implements Preparable {

    private static final long serialVersionUID = -2404576552417042445L;

    private IBaseService<Opportunity> baseService;
    private IBaseService<Account> accountService;
    private IBaseService<Currency> currencyService;
    private IOptionService<OpportunityType> opportunityTypeService;
    private IOptionService<SalesStage> salesStageService;
    private IOptionService<LeadSource> leadSourceService;
    private IBaseService<Campaign> campaignService;
    private IBaseService<Contact> contactService;
    private IBaseService<Document> documentService;
    private IBaseService<User> userService;
    private Opportunity opportunity;
    private List<OpportunityType> types;
    private List<Currency> currencies;
    private List<SalesStage> salesStages;
    private List<LeadSource> sources;
    private Integer accountID = null;
    private String accountText = null;
    private Integer typeID = null;
    private String typeLabel = "";
    private Integer currencyID = null;
    private String currencyText = "";
    private Integer salesStageID = null;
    private String salesStageLabel = "";
    private Integer sourceID = null;
    private String sourceLabel = "";
    private Integer campaignID = null;
    private String campaignText = null;
    private String expectCloseDate = null;

    /**
     * Gets the entity.
     * 
     * @return the SUCCESS result
     */
    public String get() throws Exception {
        if (this.getId() != null) {
            opportunity = baseService.getEntityById(Opportunity.class,
                    this.getId());

            Account account = opportunity.getAccount();
            if (account != null) {
                accountID = account.getId();
                accountText = account.getName();
            }
            Currency currency = opportunity.getCurrency();
            if (currency != null) {
                currencyID = currency.getId();
                currencyText = currency.getFullName();
            }
            SalesStage salesStage = opportunity.getSales_stage();
            if (salesStage != null) {
                salesStageID = salesStage.getId();
                salesStageLabel = salesStage.getLabel();
            }
            LeadSource leadSource = opportunity.getLead_source();
            if (leadSource != null) {
                sourceID = leadSource.getId();
                sourceLabel = leadSource.getLabel();
            }
            OpportunityType type = opportunity.getType();
            if (type != null) {
                typeID = type.getId();
                typeLabel = type.getLabel();
            }
            User assignedTo = opportunity.getAssigned_to();
            if (assignedTo != null) {
                this.setAssignedToID(assignedTo.getId());
                this.setAssignedToText(assignedTo.getName());
            }

            Campaign campaign = opportunity.getCampaign();
            if (campaign != null) {
                campaignID = campaign.getId();
                campaignText = campaign.getName();
            }

            Date expect_close_date = opportunity.getExpect_close_date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    Constant.DATE_EDIT_FORMAT);
            if (expect_close_date != null) {
                expectCloseDate = dateFormat.format(expect_close_date);
            }
            this.getBaseInfo(opportunity, Opportunity.class.getSimpleName(),
                    Constant.CRM_NAMESPACE);
        } else {
            this.initBaseInfo();
        }
        return SUCCESS;
    }

    /**
     * Prepares the list
     * 
     */
    public void prepare() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        String local = (String) session.get("locale");
        this.types = opportunityTypeService.getOptions(
                OpportunityType.class.getSimpleName(), local);
        this.currencies = currencyService.getAllObjects(Currency.class
                .getSimpleName());
        this.salesStages = salesStageService.getOptions(
                SalesStage.class.getSimpleName(), local);
        this.sources = leadSourceService.getOptions(
                LeadSource.class.getSimpleName(), local);
    }

    /**
     * @return the baseService
     */
    public IBaseService<Opportunity> getBaseService() {
        return baseService;
    }

    /**
     * @param baseService
     *            the baseService to set
     */
    public void setBaseService(IBaseService<Opportunity> baseService) {
        this.baseService = baseService;
    }

    /**
     * @return the accountService
     */
    public IBaseService<Account> getAccountService() {
        return accountService;
    }

    /**
     * @param accountService
     *            the accountService to set
     */
    public void setAccountService(IBaseService<Account> accountService) {
        this.accountService = accountService;
    }

    /**
     * @return the currencyService
     */
    public IBaseService<Currency> getCurrencyService() {
        return currencyService;
    }

    /**
     * @param currencyService
     *            the currencyService to set
     */
    public void setCurrencyService(IBaseService<Currency> currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * @return the campaignService
     */
    public IBaseService<Campaign> getCampaignService() {
        return campaignService;
    }

    /**
     * @param campaignService
     *            the campaignService to set
     */
    public void setCampaignService(IBaseService<Campaign> campaignService) {
        this.campaignService = campaignService;
    }

    /**
     * @return the userService
     */
    public IBaseService<User> getUserService() {
        return userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(IBaseService<User> userService) {
        this.userService = userService;
    }

    /**
     * @return the opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * @param opportunity
     *            the opportunity to set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return the types
     */
    public List<OpportunityType> getTypes() {
        return types;
    }

    /**
     * @param types
     *            the types to set
     */
    public void setTypes(List<OpportunityType> types) {
        this.types = types;
    }

    /**
     * @return the currencies
     */
    public List<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * @param currencies
     *            the currencies to set
     */
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * @return the salesStages
     */
    public List<SalesStage> getSalesStages() {
        return salesStages;
    }

    /**
     * @param salesStages
     *            the salesStages to set
     */
    public void setSalesStages(List<SalesStage> salesStages) {
        this.salesStages = salesStages;
    }

    /**
     * @return the sources
     */
    public List<LeadSource> getSources() {
        return sources;
    }

    /**
     * @param sources
     *            the sources to set
     */
    public void setSources(List<LeadSource> sources) {
        this.sources = sources;
    }

    /**
     * @return the typeID
     */
    public Integer getTypeID() {
        return typeID;
    }

    /**
     * @param typeID
     *            the typeID to set
     */
    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the currencyID
     */
    public Integer getCurrencyID() {
        return currencyID;
    }

    /**
     * @param currencyID
     *            the currencyID to set
     */
    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    /**
     * @return the salesStageID
     */
    public Integer getSalesStageID() {
        return salesStageID;
    }

    /**
     * @param salesStageID
     *            the salesStageID to set
     */
    public void setSalesStageID(Integer salesStageID) {
        this.salesStageID = salesStageID;
    }

    /**
     * @return the sourceID
     */
    public Integer getSourceID() {
        return sourceID;
    }

    /**
     * @param sourceID
     *            the sourceID to set
     */
    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }

    /**
     * @return the campaignID
     */
    public Integer getCampaignID() {
        return campaignID;
    }

    /**
     * @param campaignID
     *            the campaignID to set
     */
    public void setCampaignID(Integer campaignID) {
        this.campaignID = campaignID;
    }

    /**
     * @return the accountID
     */
    public Integer getAccountID() {
        return accountID;
    }

    /**
     * @param accountID
     *            the accountID to set
     */
    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the expectCloseDate
     */
    public String getExpectCloseDate() {
        return expectCloseDate;
    }

    /**
     * @param expectCloseDate
     *            the expectCloseDate to set
     */
    public void setExpectCloseDate(String expectCloseDate) {
        this.expectCloseDate = expectCloseDate;
    }

    /**
     * @return the contactService
     */
    public IBaseService<Contact> getContactService() {
        return contactService;
    }

    /**
     * @param contactService
     *            the contactService to set
     */
    public void setContactService(IBaseService<Contact> contactService) {
        this.contactService = contactService;
    }

    /**
     * @return the documentService
     */
    public IBaseService<Document> getDocumentService() {
        return documentService;
    }

    /**
     * @param documentService
     *            the documentService to set
     */
    public void setDocumentService(IBaseService<Document> documentService) {
        this.documentService = documentService;
    }

    /**
     * @return the accountText
     */
    public String getAccountText() {
        return accountText;
    }

    /**
     * @return the campaignText
     */
    public String getCampaignText() {
        return campaignText;
    }

    /**
     * @return the opportunityTypeService
     */
    public IOptionService<OpportunityType> getOpportunityTypeService() {
        return opportunityTypeService;
    }

    /**
     * @param opportunityTypeService
     *            the opportunityTypeService to set
     */
    public void setOpportunityTypeService(
            IOptionService<OpportunityType> opportunityTypeService) {
        this.opportunityTypeService = opportunityTypeService;
    }

    /**
     * @return the salesStageService
     */
    public IOptionService<SalesStage> getSalesStageService() {
        return salesStageService;
    }

    /**
     * @param salesStageService
     *            the salesStageService to set
     */
    public void setSalesStageService(
            IOptionService<SalesStage> salesStageService) {
        this.salesStageService = salesStageService;
    }

    /**
     * @return the leadSourceService
     */
    public IOptionService<LeadSource> getLeadSourceService() {
        return leadSourceService;
    }

    /**
     * @param leadSourceService
     *            the leadSourceService to set
     */
    public void setLeadSourceService(
            IOptionService<LeadSource> leadSourceService) {
        this.leadSourceService = leadSourceService;
    }

    /**
     * @return the typeLabel
     */
    public String getTypeLabel() {
        return typeLabel;
    }

    /**
     * @param typeLabel
     *            the typeLabel to set
     */
    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    /**
     * @return the salesStageLabel
     */
    public String getSalesStageLabel() {
        return salesStageLabel;
    }

    /**
     * @param salesStageLabel
     *            the salesStageLabel to set
     */
    public void setSalesStageLabel(String salesStageLabel) {
        this.salesStageLabel = salesStageLabel;
    }

    /**
     * @return the sourceLabel
     */
    public String getSourceLabel() {
        return sourceLabel;
    }

    /**
     * @param sourceLabel
     *            the sourceLabel to set
     */
    public void setSourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    /**
     * @param accountText
     *            the accountText to set
     */
    public void setAccountText(String accountText) {
        this.accountText = accountText;
    }

    /**
     * @param campaignText
     *            the campaignText to set
     */
    public void setCampaignText(String campaignText) {
        this.campaignText = campaignText;
    }

    /**
     * @return the currencyText
     */
    public String getCurrencyText() {
        return currencyText;
    }

    /**
     * @param currencyText
     *            the currencyText to set
     */
    public void setCurrencyText(String currencyText) {
        this.currencyText = currencyText;
    }

}
