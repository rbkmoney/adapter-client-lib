package com.rbkmoney.hellgate.party.management;


import com.rbkmoney.damsel.base.InvalidRequest;
import com.rbkmoney.damsel.domain.*;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.payment_processing.*;
import com.rbkmoney.hellgate.party.management.exception.HellgatePartyManagementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class HellgatePartyManagementClient implements PartyManagementSrv.Iface {

    private final PartyManagementSrv.Iface partyManagement;


    /* Party */

    @Override
    public void create(UserInfo userInfo, String partyId, PartyParams partyParams) throws InvalidUser, PartyExists, TException {
        log.info("Create party by userInfo: {}, partyId {}, partyParams {}", userInfo, partyId, partyParams);
        try {
            partyManagement.create(userInfo, partyId, partyParams);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't create partyManagement with  userInfo: %s, partyId %s, partyParams %s", userInfo, partyId, partyParams), ex);
        }
    }

    @Override
    public Party get(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, TException {
        log.info("Get party by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            return partyManagement.get(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't get partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public long getRevision(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, TException {
        log.info("Get revision by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            return partyManagement.getRevision(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't get revision partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public Party checkout(UserInfo userInfo, String partyId, PartyRevisionParam partyRevisionParam) throws InvalidUser, PartyNotFound, InvalidPartyRevision, TException {
        log.info("Checkout by userInfo: {}, partyId {}, partyRevisionParam {}", userInfo, partyId, partyRevisionParam);
        try {
            return partyManagement.checkout(userInfo, partyId, partyRevisionParam);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't checkout partyManagement with  userInfo: %s, partyId %s, partyRevisionParam %s", userInfo, partyId, partyRevisionParam), ex);
        }
    }

    @Override
    public void suspend(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        log.info("Suspend by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            partyManagement.suspend(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't suspend partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public void activate(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        log.info("activate by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            partyManagement.activate(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't activate partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public void block(UserInfo userInfo, String partyId, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        log.info("block by userInfo: {}, partyId {}, reason {}", userInfo, partyId, reason);
        try {
            partyManagement.block(userInfo, partyId, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't block partyManagement with  userInfo: %s, partyId %s, reason %s", userInfo, partyId, reason), ex);
        }
    }

    @Override
    public void unblock(UserInfo userInfo, String partyId, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, TException {
        log.info("unblock by userInfo: {}, partyId {}, reason {}", userInfo, partyId, reason);
        try {
            partyManagement.unblock(userInfo, partyId, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't unblock partyManagement with  userInfo: %s, partyId %s, reason %s", userInfo, partyId, reason), ex);
        }
    }

    /* Party Meta */

    @Override
    public Map<String, Value> getMeta(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, TException {
        log.info("getMeta by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            return partyManagement.getMeta(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getMeta partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public Value getMetaData(UserInfo userInfo, String partyId, String partyMetaNamespace) throws InvalidUser, PartyNotFound, PartyMetaNamespaceNotFound, TException {
        log.info("getMetaData by userInfo: {}, partyId {}, partyMetaNamespace {}", userInfo, partyId, partyMetaNamespace);
        try {
            return partyManagement.getMetaData(userInfo, partyId, partyMetaNamespace);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getMetaData partyManagement with  userInfo: %s, partyId %s, partyMetaNamespace %s", userInfo, partyId, partyMetaNamespace), ex);
        }
    }

    @Override
    public void setMetaData(UserInfo userInfo, String partyId, String partyMetaNamespace, Value partyMetaData) throws InvalidUser, PartyNotFound, TException {
        log.info("setMetaData by userInfo: {}, partyId {}, partyMetaNamespace {}, partyMetaData {}", userInfo, partyId, partyMetaNamespace, partyMetaData);
        try {
            partyManagement.setMetaData(userInfo, partyId, partyMetaNamespace, partyMetaData);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't setMetaData partyManagement with  userInfo: %s, partyId %s, partyMetaNamespace %s", userInfo, partyId, partyMetaNamespace), ex);
        }
    }

    @Override
    public void removeMetaData(UserInfo userInfo, String partyId, String partyMetaNamespace) throws InvalidUser, PartyNotFound, PartyMetaNamespaceNotFound, TException {
        log.info("removeMetaData by userInfo: {}, partyId {}, partyMetaNamespace {}", userInfo, partyId, partyMetaNamespace);
        try {
            partyManagement.removeMetaData(userInfo, partyId, partyMetaNamespace);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't removeMetaData partyManagement with  userInfo: %s, partyId %s, partyMetaNamespace %s", userInfo, partyId, partyMetaNamespace), ex);
        }
    }

    /* Contract */

    @Override
    public Contract getContract(UserInfo userInfo, String partyId, String сontractID) throws InvalidUser, PartyNotFound, ContractNotFound, TException {
        log.info("getContract by userInfo: {}, partyId {}, сontractID {}", userInfo, partyId, сontractID);
        try {
            return partyManagement.getContract(userInfo, partyId, сontractID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getContract partyManagement with  userInfo: %s, partyId %s, сontractID %s", userInfo, partyId, сontractID), ex);
        }
    }

    @Override
    public TermSet computeContractTerms(UserInfo userInfo, String partyId, String сontractID, String timestamp) throws InvalidUser, PartyNotFound, PartyNotExistsYet, ContractNotFound, TException {
        log.info("computeContractTerms by userInfo: {}, partyId {}, сontractID {}, timestamp {}", userInfo, partyId, сontractID, timestamp);
        try {
            return partyManagement.computeContractTerms(userInfo, partyId, сontractID, timestamp);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computeContractTerms partyManagement with  userInfo: %s, partyId %s, сontractID %s, timestamp %s", userInfo, partyId, сontractID, timestamp), ex);
        }
    }

    /* Shop */

    @Override
    public Shop getShop(UserInfo userInfo, String partyId, String shopID) throws InvalidUser, PartyNotFound, ShopNotFound, TException {
        log.info("getShop by userInfo: {}, partyId {}, shopID {}", userInfo, partyId, shopID);
        try {
            return partyManagement.getShop(userInfo, partyId, shopID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getShop partyManagement with  userInfo: %s, partyId %s, shopID %s", userInfo, partyId, shopID), ex);
        }
    }

    @Override
    public void suspendShop(UserInfo userInfo, String partyId, String shopID) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        log.info("suspendShop by userInfo: {}, partyId {}, shopID {}", userInfo, partyId, shopID);
        try {
            partyManagement.suspendShop(userInfo, partyId, shopID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't suspendShop partyManagement with  userInfo: %s, partyId %s, shopID %s", userInfo, partyId, shopID), ex);
        }
    }

    @Override
    public void activateShop(UserInfo userInfo, String partyId, String shopID) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        log.info("activateShop by userInfo: {}, partyId {}, shopID {}", userInfo, partyId, shopID);
        try {
            partyManagement.activateShop(userInfo, partyId, shopID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't activateShop partyManagement with  userInfo: %s, partyId %s, shopID %s", userInfo, partyId, shopID), ex);
        }
    }

    @Override
    public void blockShop(UserInfo userInfo, String partyId, String shopID, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        log.info("blockShop by userInfo: {}, partyId {}, shopID {}, reason {}", userInfo, partyId, shopID, reason);
        try {
            partyManagement.blockShop(userInfo, partyId, shopID, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't blockShop partyManagement with  userInfo: %s, partyId %s, shopID %s, reason %s", userInfo, partyId, shopID, reason), ex);
        }
    }

    @Override
    public void unblockShop(UserInfo userInfo, String partyId, String shopID, String reason) throws InvalidUser, PartyNotFound, ShopNotFound, InvalidShopStatus, TException {
        log.info("unblockShop by userInfo: {}, partyId {}, shopID {}, reason {}", userInfo, partyId, shopID, reason);
        try {
            partyManagement.unblockShop(userInfo, partyId, shopID, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't unblockShop partyManagement with  userInfo: %s, partyId %s, shopID %s, reason %s", userInfo, partyId, shopID, reason), ex);
        }
    }

    @Override
    public TermSet computeShopTerms(UserInfo userInfo, String partyId, String shopID, String timestamp) throws InvalidUser, PartyNotFound, PartyNotExistsYet, ShopNotFound, TException {
        log.info("computeShopTerms by userInfo: {}, partyId {}, shopID {}, timestamp {}", userInfo, partyId, shopID, timestamp);
        try {
            return partyManagement.computeShopTerms(userInfo, partyId, shopID, timestamp);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computeShopTerms partyManagement with  userInfo: %s, partyId %s, shopID %s, timestamp %s", userInfo, partyId, shopID, timestamp), ex);
        }
    }

    /* Wallet */

    @Override
    public TermSet computeWalletTermsNew(UserInfo userInfo, String partyId, String contractID, String timestamp, Varset varset) throws InvalidUser, PartyNotFound, PartyNotExistsYet, TException {
        log.info("computeShopTerms by userInfo: {}, partyId {}, contractID {}, timestamp {}, varset {}", userInfo, partyId, contractID, timestamp, varset);
        try {
            return partyManagement.computeWalletTermsNew(userInfo, partyId, contractID, timestamp, varset);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computeShopTerms partyManagement with  userInfo: %s, partyId %s, contractID %s, timestamp %s, varset %s", userInfo, partyId, contractID, timestamp, varset), ex);
        }
    }

    @Override
    public TermSet computeWalletTerms(UserInfo userInfo, String partyId, String contractID, String walletID, CurrencyRef currency, String timestamp) throws InvalidUser, PartyNotFound, PartyNotExistsYet, TException {
        log.info("computeWalletTerms by userInfo: {}, partyId {}, contractID {}, walletID {}, currency {}, timestamp {}", userInfo, partyId, contractID, walletID, currency, timestamp);
        try {
            return partyManagement.computeWalletTerms(userInfo, partyId, contractID, walletID, currency, timestamp);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computeWalletTerms partyManagement with  userInfo: %s, partyId %s, contractID %s, walletID %s, currency %s, timestamp %s", userInfo, partyId, contractID, walletID, currency, timestamp), ex);
        }
    }

    /* Claim */

    @Override
    public Claim createClaim(UserInfo userInfo, String partyId, List<PartyModification> list) throws InvalidUser, PartyNotFound, InvalidPartyStatus, ChangesetConflict, InvalidChangeset, InvalidRequest, TException {
        log.info("createClaim by userInfo: {}, partyId {},list {}", userInfo, partyId, list);
        try {
            return partyManagement.createClaim(userInfo, partyId, list);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't createClaim partyManagement with  userInfo: %s, partyId %s, list %s", userInfo, partyId, list), ex);
        }
    }

    @Override
    public Claim getClaim(UserInfo userInfo, String partyId, long claimID) throws InvalidUser, PartyNotFound, ClaimNotFound, TException {
        log.info("getClaim by userInfo: {}, partyId {}, claimID {}", userInfo, partyId, claimID);
        try {
            return partyManagement.getClaim(userInfo, partyId, claimID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getClaim partyManagement with  userInfo: %s, partyId %s, claimID %s", userInfo, partyId, claimID), ex);
        }
    }

    @Override
    public List<Claim> getClaims(UserInfo userInfo, String partyId) throws InvalidUser, PartyNotFound, TException {
        log.info("get claims by userInfo: {}, partyId {}", userInfo, partyId);
        try {
            return partyManagement.getClaims(userInfo, partyId);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't get claims partyManagement with  userInfo: %s, partyId %s", userInfo, partyId), ex);
        }
    }

    @Override
    public void acceptClaim(UserInfo userInfo, String partyId, long claimID, int revision) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, InvalidClaimRevision, InvalidChangeset, TException {
        log.info("acceptClaim by userInfo: {}, partyId {}, claimID {}, revision {}", userInfo, partyId, claimID, revision);
        try {
            partyManagement.acceptClaim(userInfo, partyId, claimID, revision);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't acceptClaim partyManagement with  userInfo: %s, partyId %s, claimID %s, revision %s", userInfo, partyId, claimID, revision), ex);
        }
    }

    @Override
    public void updateClaim(UserInfo userInfo, String partyId, long claimID, int revision, List<PartyModification> list) throws InvalidUser, PartyNotFound, InvalidPartyStatus, ClaimNotFound, InvalidClaimStatus, InvalidClaimRevision, ChangesetConflict, InvalidChangeset, InvalidRequest, TException {
        log.info("updateClaim by userInfo: {}, partyId {}, claimID {}, revision {}, list {}", userInfo, partyId, claimID, revision, list);
        try {
            partyManagement.updateClaim(userInfo, partyId, claimID, revision, list);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't updateClaim partyManagement with  userInfo: %s, partyId %s, claimID %s, revision %s", userInfo, partyId, claimID, revision), ex);
        }
    }

    @Override
    public void denyClaim(UserInfo userInfo, String partyId, long claimID, int revision, String reason) throws InvalidUser, PartyNotFound, ClaimNotFound, InvalidClaimStatus, InvalidClaimRevision, TException {
        log.info("denyClaim by userInfo: {}, partyId {}, claimID {}, revision {}, reason {}", userInfo, partyId, claimID, revision, reason);
        try {
            partyManagement.denyClaim(userInfo, partyId, claimID, revision, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't denyClaim partyManagement with  userInfo: %s, partyId %s, claimID %s, revision %s, reason %s", userInfo, partyId, claimID, revision, reason), ex);
        }
    }

    @Override
    public void revokeClaim(UserInfo userInfo, String partyId, long claimID, int revision, String reason) throws InvalidUser, PartyNotFound, InvalidPartyStatus, ClaimNotFound, InvalidClaimStatus, InvalidClaimRevision, TException {
        log.info("revokeClaim by userInfo: {}, partyId {}, claimID {}, revision {}, reason {}", userInfo, partyId, claimID, revision, reason);
        try {
            partyManagement.revokeClaim(userInfo, partyId, claimID, revision, reason);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't revokeClaim partyManagement with  userInfo: %s, partyId %s, claimID %s, revision %s, reason %s", userInfo, partyId, claimID, revision, reason), ex);
        }
    }

    /* Event polling */

    @Override
    public List<Event> getEvents(UserInfo userInfo, String partyId, EventRange eventRange) throws InvalidUser, PartyNotFound, EventNotFound, InvalidRequest, TException {
        log.info("getEvents by userInfo: {}, partyId {}, eventRange {}", userInfo, partyId, eventRange);
        try {
            return partyManagement.getEvents(userInfo, partyId, eventRange);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getEvents partyManagement with  userInfo: %s, partyId %s, eventRange %s", userInfo, partyId, eventRange), ex);
        }
    }

    /* Accounts */

    @Override
    public ShopAccount getShopAccount(UserInfo userInfo, String partyId, String shopID) throws InvalidUser, PartyNotFound, ShopNotFound, ShopAccountNotFound, TException {
        log.info("getShopAccount by userInfo: {}, partyId {}, shopID {}", userInfo, partyId, shopID);
        try {
            return partyManagement.getShopAccount(userInfo, partyId, shopID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getShopAccount partyManagement with  userInfo: %s, partyId %s, shopID %s", userInfo, partyId, shopID), ex);
        }
    }

    @Override
    public AccountState getAccountState(UserInfo userInfo, String partyId, long accountID) throws InvalidUser, PartyNotFound, AccountNotFound, TException {
        log.info("getAccountState by userInfo: {}, partyId {}, accountID {}", userInfo, partyId, accountID);
        try {
            return partyManagement.getAccountState(userInfo, partyId, accountID);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't getAccountState partyManagement with  userInfo: %s, partyId %s, accountID %s", userInfo, partyId, accountID), ex);
        }
    }

    /* Payment institutions */

    @Override
    public TermSet computePaymentInstitutionTerms(UserInfo userInfo, String partyId, PaymentInstitutionRef paymentInstitutionRef, Varset varset) throws InvalidUser, PartyNotFound, PaymentInstitutionNotFound, TException {
        log.info("computePaymentInstitutionTerms by userInfo: {}, partyId {}, paymentInstitutionRef {}, varset {}", userInfo, partyId, paymentInstitutionRef, varset);
        try {
            return partyManagement.computePaymentInstitutionTerms(userInfo, partyId, paymentInstitutionRef, varset);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computePaymentInstitutionTerms partyManagement with  userInfo: %s, partyId %s, paymentInstitutionRef %s, varset %s", userInfo, partyId, paymentInstitutionRef, varset), ex);
        }
    }

    /* Payouts */

    @Override
    public List<FinalCashFlowPosting> computePayoutCashFlow(UserInfo userInfo, String partyId, PayoutParams payoutParams) throws InvalidUser, PartyNotFound, PartyNotExistsYet, ShopNotFound, OperationNotPermitted, TException {
        log.info("computePayoutCashFlow by userInfo: {}, partyId {}, payoutParams {}", userInfo, partyId, payoutParams);
        try {
            return partyManagement.computePayoutCashFlow(userInfo, partyId, payoutParams);
        } catch (TException ex) {
            throw new HellgatePartyManagementException(String.format("Can't computePayoutCashFlow partyManagement with  userInfo: %s, partyId %s, payoutParams %s", userInfo, partyId, payoutParams), ex);
        }
    }
}
