package me.jcala.xmarket.data.api;

import java.util.List;

import lombok.Getter;
import me.jcala.xmarket.conf.ApiConf;
import me.jcala.xmarket.data.dto.MsgDto;
import me.jcala.xmarket.data.dto.Result;
import me.jcala.xmarket.data.pojo.Message;
import me.jcala.xmarket.data.pojo.Trade;
import me.jcala.xmarket.data.pojo.TradeTag;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface TradeReq {

     int GET_TAG=1;
     int GET_NAME=2;

    /**
     * 获取分类列表请求(List<TradeTag>)
     *
     * kind设置为1
     */
    @GET(ApiConf.get_tags)
    Observable<Result<List<TradeTag>>> tradeTags(@Query("kind") int kind);


    /**
     * 获取分类名称列表请求(List<String>)
     *
     * kind设置为2
     */
    @GET(ApiConf.get_tags)
    Observable<Result<List<String>>> tagNames(@Query("kind") int kind);

    /**
     * 根据商品Id获取商品详细信息
     */
    @GET(ApiConf.get_trade)
    Observable<Result<Trade>> tradeDetail(@Path("tradeId") String tradeId);

    /**
     * 根据学校名称获取本校在售商品列表
     */
    @GET(ApiConf.get_school_trades)
    Observable<Result<List<Trade>>> getSchoolTrades(
            @Path("schoolName")  String school,
            @Query("page") int page,
            @Query("size") int size
    );

    /**
     * 发布商品
     */
    @Multipart
    @POST(ApiConf.create_trade)
    Observable<Result<String>> addTrade(
            @Path("userId")String userId,
            @Part("trade") RequestBody trade,
            @Part List<MultipartBody.Part> parts);

    /**
     * 根据分类获取在售商品列表
     */
    @GET(ApiConf.get_tag_trades)
    Observable<Result<List<Trade>>> getTrades(
            @Path("tagName")  String tagName,
            @Query("page") int page,
            @Query("size") int size
    );
}
