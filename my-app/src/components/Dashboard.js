import React, { Component } from 'react'
import ProjectItem from './project/ProjectItem';
import Header from './Layout/Header';

 class Dashboard extends Component {
  render() {
    return (
        <div>
      <h1 className = "alert alert-warning">BLAH</h1>
      <ProjectItem />
      <Header />
      </div>
    )
  }
}

export default Dashboard;
