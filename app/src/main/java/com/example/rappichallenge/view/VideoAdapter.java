package com.example.rappichallenge.view;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rappichallenge.R;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.model.Video;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    // Store a member variable for the contacts
    private List<Video> videoList;
    // Store the context for easy access
    private Context mContext;

    private Lifecycle lifecycle;

    private VideoCellInteractionListener videoCellInteractionListener;

    public VideoAdapter(List<Video> videoList, Context mContext, VideoCellInteractionListener videoCellInteractionListener, Lifecycle lifecycle) {
        this.videoList = videoList;
        this.mContext = mContext;
        this.videoCellInteractionListener = videoCellInteractionListener;
        this.lifecycle = lifecycle;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_cell, parent, false);

        VideoViewHolder videoViewHolder = new VideoViewHolder(itemView);
        return videoViewHolder;
    }


    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        VideoViewHolder movieViewHolder = (VideoViewHolder) holder;
        movieViewHolder.bindProduct(video, mContext);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder{

        private YouTubePlayerView youTubePlayerView;
        private View view;


        public VideoViewHolder(View itemView) {
            super(itemView);
            youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_player_view);

            view = itemView;

        }

        public void bindProduct(final Video video, final Context context){

            lifecycle.addObserver(youTubePlayerView);

            youTubePlayerView.getPlayerUIController().showFullscreenButton(false);
            youTubePlayerView.initialize(new YouTubePlayerInitListener() {
                @Override
                public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                    initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            String videoId = video.getKey();
                            initializedYouTubePlayer.cueVideo(videoId, 0);
                        }
                    });
                }
            }, true);

         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoCellInteractionListener.movieCellInteraction(video);
                }
            });*/
        }
    }
    public interface VideoCellInteractionListener {
        // TODO: Update argument type and name
        void videoCellInteraction(Result result);
    }

}